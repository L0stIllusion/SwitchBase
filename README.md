# SwitchBase

[![](https://jitpack.io/v/depressingillusion/Bot_Base.svg)](https://jitpack.io/#depressingillusion/Bot_Base)

SwitchBase is a base for Discord bots. This base is based on [Bastian's Javacord Audio Fork](https://github.com/Bastian/Lavaplayer-Wrapper)!

# Features!
  - Easily customizably with several interfaces for you to implement.
  - Easy setup with aforementioned customizability
    
# Installation
This project is currently hosted on Jitpack.io.
#### Gradle
##### Repositories
```gradle
repositories {
	...
	maven { url 'https://jitpack.io' }
}
```
##### Dependencies
```gradle
dependencies {
        implementation 'com.github.depressingillusion:Bot_Base:Tag'
}
```
And that's all, you may now use the base in your bot! If you use another build manager go to the [jitpack.io](https://jitpack.io/#depressingillusion/Bot_Base) site, and follow the instructions for your build manager.

# Examples
To quickly get started wtih your bot, here's a quickstart.
```java
public class MyEpicCoolBot {
  public static void main(String... args) {
    BotBaseBuilder.withApi(new DiscordApiBuilder()
            .setToken("xxx")
            .login()
            .join())
            .setCommandCore(new CommandCoreImpl())
            .setPrefix("!")
            .build();
  }
}
```
Now you may be reading that and scratching your head trying to figure out what CommandCoreImpl is it's actually a very simple class. The CommandCore interface is what the base uses to track all your commands and such! Since there are a few ways one might want to add commands to their bot, you can create your own implementation as such!
```java
public class CommandCoreImpl implements CommandCore {
  private static List<Command> commands = new ArrayList<>();
  @Override
  public void initCommands() {
    commands.add(new Test());
  }

  @Override
  public List<Command> getCommmands() {
    return commands;
  }
}
```
Awesome, now you have a functioning bot, but you don't have commands :( Luckily it's fairly simple. Here's a another quick example!
```java
public class Test extends Command {
  public Test() {
    super(
            "test", //Command Key
            "Returns a test message", //Brief Description
            "Returns a message to check if the bot is running", //Detailed Description
            "!test", //Example
            CommandCategory.NONE, //Command Category
            new Parameters()); //Paramaters of the bot
  }

  @Override
  public void executeCommand(UserMess mess, DiscordApi api) throws CommandException {
    mess.sendMessage("Hello!");
  }
  ```
  Once you have your command add it your CommandCore implementation, and you have your bot ready to go!
