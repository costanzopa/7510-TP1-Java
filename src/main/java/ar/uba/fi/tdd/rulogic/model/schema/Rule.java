package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by costa on 29/10/2017.
 * asdadasd
 */
public class Rule extends Element {
    private static final java.lang.String RULE_PATTERN = ":-";
    private Fact leftSide;
    private List<Element> rightSide;

    Rule(String content) {
        this.setLine(content);
        this.setLeftSide(content);
        this.rightSide = null;
        if (this.leftSide != null && this.leftSide.isValid()) {
            this.setRightSide(content);
        }
    }

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
            this.rightSide = new ArrayList<>();
            for (String aSplittedRightSide : splittedRightSide) {
                Element element = new Fact(aSplittedRightSide);
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

}
