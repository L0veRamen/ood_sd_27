package ca.bytetube._16_alexa;

public interface Command {
    boolean validate();
    void execute();
}
