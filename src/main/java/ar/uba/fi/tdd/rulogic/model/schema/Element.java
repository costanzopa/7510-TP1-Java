package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.List;

/**
 * Created by costa on 28/10/2017.
 * Element abstract Class.
 */
public abstract class Element {
    String name;
    private String line;
    List<String> arguments;
    private boolean valid;


    void setLine(String line) {
        this.line = line;
        this.setIsValid(line);
        if (this.isValid()) {
            this.setName(line);
            this.setArguments(line);
        }
    }

    void setIsValid(boolean valid) {
        this.valid = valid;
    }

    protected abstract void setIsValid(String isValid);

    protected abstract void setName(String name);

    protected abstract void setArguments(String arguments);

    String getName() {
        return this.name;
    }

    public String getLine() {
        return line;
    }

    List<String> getArguments() {
        return this.arguments;
    }


    boolean isValid() {
        return valid;
    }

    public abstract boolean evaluate(Element element);

}
