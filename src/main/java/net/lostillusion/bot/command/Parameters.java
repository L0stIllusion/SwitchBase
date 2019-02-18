package net.lostillusion.bot.command;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class Parameters {
    private final List<Parameter> parameterList = new LinkedList<>();

    public Parameters(Parameter... parameters) {
        addParamaters(parameters);
    }

    public Parameters addParamaters(Parameter... parameters) {
        parameterList.addAll(List.of(parameters));
        return this;
    }
    public Parameters addParamater(Parameter parameter) {
        parameterList.add(parameter);
        return this;
    }

    public List<Parameter> returnParamaters() {
        return parameterList;
    }

    @Override
    public String toString() {
        return parameterList.stream().map(Parameter::toString).collect(Collectors.joining(" "));
    }
    public final static class Parameter {
        private final boolean required;
        private final String paramater;
        public Parameter(Boolean required, String paramater) {
            this.required = required;
            this.paramater = paramater;
        }

        public boolean isRequired() {
            return required;
        }

        public String getParamater() {
            return paramater;
        }

        @Override
        public String toString() {
            return required ? String.format("[%s]", paramater) : String.format("{%s}",paramater);
        }
    }
}
