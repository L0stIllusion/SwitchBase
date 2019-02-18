package net.lostillusion.bot.command;

public abstract class CommandInfo {
    private String key;
    private String briefDescription;
    private String detailedDescription;
    private String example;
    private CommandCategory commandCategory;
    private Parameters parameters;

    public CommandInfo(String key, String briefDescription, String detailedDescription, String example, CommandCategory commandCategory, Parameters parameters) {
        this.key = key;
        this.briefDescription = briefDescription;
        this.detailedDescription = detailedDescription;
        this.example = example;
        this.commandCategory = commandCategory;
        this.parameters = new Parameters(parameters.returnParamaters().toArray(new Parameters.Parameter[0]));
    }

    public boolean hasParamaters() {
        return !parameters.returnParamaters().isEmpty();
    }

    public String getKey() {
        return key;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public String getExample() {
        return example;
    }

    public CommandCategory getCommandCategory() {
        return commandCategory;
    }

    public Parameters getParameters() {
        return parameters;
    }
}
