package net.lostillusion.bot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import net.lostillusion.bot.command.CommandInfo;
import org.postgresql.Driver;
import org.postgresql.jdbc.PgConnection;
import org.postgresql.util.HostSpec;

public class Database {
    private final String database;
    private final String CONNECTION_URL;
    private Properties login = new Properties();

    private Database(String url) {
        CONNECTION_URL = url;
        database = "";
    }

    private Database(String url, String user, String pass, String database) {
        this.database = database;
        CONNECTION_URL = url;
        login.put("user", user);
        login.put("password", pass);
    }

    public static Database asPostgreSQL(String database, String user, String pass) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return new Database("jdbc:postgresql:" + database, user, pass, database);
    }

    /**
     * @param function Applies this function to the given argument.
     * @param <T> Expected return type
     * @return the expected function result
     * @throws SQLProcessingException when during apply function throws. SQLProcessingException will swallow if not try-catched
     */
    public <T> T connect(SQLFunction<PostgreConnection, T> function) throws SQLProcessingException {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, login);
            PostgreConnection connection = new PostgreConnection(login, CONNECTION_URL, database, (PgConnection) conn)) {
            return function.apply(connection);
        } catch (SQLException e) {
            throw new SQLProcessingException(e);
        }
    }

    public void connect(SQLConsumer<PostgreConnection> consumer) throws SQLProcessingException {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, login);
             PostgreConnection connection = new PostgreConnection(login, CONNECTION_URL, database, (PgConnection) conn)) {
                consumer.accept(connection);
        } catch (SQLException e) {
            throw new SQLProcessingException(e);
        }
    }

    public boolean statement(String query) {
        return connect(connection -> {
            try(Statement statement = connection.createStatement()) {
                statement.executeQuery(query);
                return true;
            } catch (SQLException e) {
                return false;
            }
        });
    }
    public static class SQLProcessingException extends RuntimeException {
        public SQLProcessingException(Throwable exception) {
            super(exception);
        }
    }

    public static class PostgreConnection extends PgConnection {
        public PostgreConnection(Properties props, String connectionURL, String database, PgConnection connection) throws SQLException {
            super(hostSpecs(Driver.parseURL(connectionURL, props)), connection.getUserName(), database, connection.getClientInfo(), connection.getURL());
        }

        public <T> T queryAndGetResults(SQLFunction<ResultSet, T> function, String sql) {
            try {
                try (ResultSet resultSet = createStatement().executeQuery(sql)) {
                    if(!resultSet.next()) return null;
                    return function.apply(resultSet);
                }
            } catch (Exception e) {
                throw new SQLProcessingException(e);
            }
        }
        private static HostSpec[] hostSpecs(Properties props) {
            String[] hosts = props.getProperty("PGHOST").split(",");
            String[] ports = props.getProperty("PGPORT").split(",");
            HostSpec[] hostSpecs = new HostSpec[hosts.length];
            for (int i = 0; i < hostSpecs.length; ++i) {
                hostSpecs[i] = new HostSpec(hosts[i], Integer.parseInt(ports[i]));
            }
            return hostSpecs;
        }
    }
    public interface SQLFunction<T, R> {
        R apply(T t) throws SQLException;
    }
    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }
}
