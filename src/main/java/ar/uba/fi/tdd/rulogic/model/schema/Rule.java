package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.List;

/**
 * Created by costa on 29/10/2017.
 * asdadasd
 */
public class Rule implements Element {
    private static final java.lang.String RULE_PATTERN = ":-";
    private boolean valid;
    private Fact leftSide;
    private List<Element> rightSide;
    private String line;

    Rule(String content) {
        this.line = content;
        this.setLeftSide(content);
        if (this.leftSide != null && this.leftSide.isValid()) {
            this.setRightSide(content);
        }
    }

    //TODO Implement Setters
    private void setLeftSide(String content) {
        String[] splittedContent = content.split(RULE_PATTERN);
        this.leftSide = new Fact(splittedContent[0]);
        this.setValid(leftSide.isValid());
    }

    private void setRightSide(String content) {
        String[] splittedContent = content.split(RULE_PATTERN);
        if (splittedContent.length > 1) {
            String auxiliarRightSideString = splittedContent[1];
            String[] splittedRightSide = auxiliarRightSideString.replaceAll("\\),","):").split(":");
            for (int i = 0; i < splittedRightSide.length; i++) {
                Element element = new Fact(splittedRightSide[i]);
                if (!element.isValid()) {
                    this.setValid(element.isValid());
                    break;
                }
                this.rightSide.add(element);
            }
        }
    }

    @Override
    public boolean evaluate(Element element) {
        return (element.getName()).equals(this.getName());
    }

    @Override
    public boolean isValid() {
        return this.valid;
    }

    @Override
    public String getName() {
        return this.leftSide.getName();
    }

    @Override
    public List<String> getArguments() {
        return this.leftSide.getArguments();
    }

    @Override
    public List<Element> getRightSide() {
        return this.rightSide;
    }

    private void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getLine() {return this.line;}

}
