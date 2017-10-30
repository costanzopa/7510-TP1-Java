package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by costa on 29/10/2017.
 * asdadasd
 */
public class Rule implements Element {
    private String line;
    private boolean valid;
    private Fact leftSide;
    private List<Element> rightSide;

    Rule(String content) {
        this.line = content;
        this.setLeftSide(content);
        if (this.leftSide != null && this.leftSide.isValid()) {
            this.setRightSide(content);
        }
    }

    //TODO Implement Setters
    private void setLeftSide(String leftSide) {
        this.leftSide = null;
    }

    private void setRightSide(String rigthSide) {
        this.rightSide = null;
    }

    @Override
    public boolean evaluate(Element element) {
        return false;
    }

    @Override
    public boolean isValid() {
        return this.valid;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getArguments() {
        return null;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
