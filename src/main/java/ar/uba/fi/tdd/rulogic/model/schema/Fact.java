package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by costa on 28/10/2017.
 * Fact extends Element
 */
public class Fact extends Element {

    private final String FACT_PATTERN = "^([^\\(\\)]*)\\(([^\\(\\)]*)\\)(\\s+)?\\.?$";

    Fact(String content) {
        this.setLine(content);
    }

    protected void setIsValid(String line) {
        boolean valid = this.isFact(line);
        this.setIsValid(valid);
    }

    private boolean isFact(String line) {
        line = line.trim();
        return Pattern.matches(FACT_PATTERN,line);
    }

    protected void setName(String line) {
        this.name = line.substring(0, line.indexOf('('));
    }

    protected void setArguments(String arguments) {
        this.arguments = null;
        String betweenParentheses = arguments.substring(arguments.indexOf('(') + 1, arguments.indexOf(')'));
        if (betweenParentheses.isEmpty()) {
            return;
        }
        String[] splitedArguments = betweenParentheses.split(",");
        this.arguments = Arrays.asList(splitedArguments);
        this.arguments = this.arguments.stream().map(String::trim).collect(Collectors.toList());
    }

    public boolean evaluate(Element element) {
        return (this.getName().equals(element.getName())
                && this.getArguments().equals(element.getArguments()));
    }

}
