package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.List;

/**
 * Created by costa on 28/10/2017.
 * Element Interface.
 */
public abstract class Element {
    private String line;
    private boolean valid;

    public String getLine() {
        return line;
    }
    protected void setLine(String line) {this.line = line;}

    public boolean isValid() {
        return this.valid;
    }
    void setValid(boolean valid) {
        this.valid = valid;
    }


    public abstract boolean evaluate(Element element);

    public abstract String getName();
    public abstract List<String> getArguments();
    public abstract List<Element> getRightSide();


}
