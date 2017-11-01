package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by costa on 28/10/2017.
 * Fact extends Element
 */
public class Fact implements Element {

    private final String FACT_PATTERN = "^([^\\(\\)]*)\\(([^\\(\\)]*)\\)(\\s+)?\\.?$";
    private String name;
    private String line;
    private List<String> arguments;
    private boolean valid;

    Fact(String content) {
        this.setLine(content);
    }

    private void setIsValid(String line) {
        boolean valid = this.isFact(line);
        this.setIsValid(valid);
    }

    private boolean isFact(String line) {
        line = line.trim();
        return Pattern.matches(FACT_PATTERN,line);
    }


    private boolean equals(Element element) {
        return (this.getName().equals(element.getName())
                && this.getArguments().equals(element.getArguments()));
    }

    private void setLine(String line) {
        this.line = line;
        this.setIsValid(line);
        if (this.isValid()) {
            this.setName(line);
            this.setArguments(line);
        }
    }

    private void setIsValid(boolean valid) {
        this.valid = valid;
    }

    private void setName(String line) {
        this.name = line.substring(0, line.indexOf('('));
    }

    private void setArguments(String arguments) {
        this.arguments = null;
        String betweenParentheses = arguments.substring(arguments.indexOf('(') + 1, arguments.indexOf(')'));
        if (betweenParentheses.isEmpty()) {
            return;
        }
        String[] splitedArguments = betweenParentheses.split(",");
        this.arguments = Arrays.asList(splitedArguments);
        this.arguments = this.arguments.stream().map(String::trim).collect(Collectors.toList());
    }

    @Override
    public boolean evaluate(Element element) {
        return (this.equals(element));
    }

    public String getName() {
        return this.name;
    }

    public String getLine() {
        return line;
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    @Override
    public List<Element> getRightSide() {
        return null;
    }


    public boolean isValid() {
        return valid;
    }

}
