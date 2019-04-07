package net.lostillusion.bot.command;

public abstract class CommandInfo {
    private String key;
    private String briefDescription;
    private String example;
    private Parameters parameters;

    CommandInfo(String key, String briefDescription, String example, Parameters parameters) {
        this.key = key;
        this.briefDescription = briefDescription;
        this.example = example;
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

    public String getExample() {
        return example;
    }

    public Parameters getParameters() {
        return parameters;
    }
}
