package net.lostillusion.bot.implementations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import net.lostillusion.bot.command.Command;
import net.lostillusion.bot.interfaces.CommandCore;
import org.atteo.classindex.ClassFilter;
import org.atteo.classindex.ClassIndex;

public class DefaultCommandCoreImpl implements CommandCore {
    public static List<Command> commands = new ArrayList<>();

    @Override
    public void initCommands() {
      ClassFilter.any(klass -> !Modifier.isAbstract(klass.getModifiers()))
              .from(ClassIndex.getSubclasses(Command.class))
              .forEach(commandClass -> {
                try {
                  commands.add(commandClass.getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                  e.printStackTrace();
                }
              });
    }

    @Override
    public List<Command> getCommmands() {
      return commands;
    }
}
