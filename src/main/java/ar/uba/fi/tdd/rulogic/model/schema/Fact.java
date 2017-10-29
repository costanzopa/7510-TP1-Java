package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by costa on 28/10/2017.
 */
public class Fact implements Element {

    private String line;
    private String name;
    private List<String> arguments;
    private boolean valid;
    private final String FACT_PATTERN = "^([^\\(\\)]*)\\(([^\\(\\)]*)\\)\\.?$";

    Fact(String content) {
      this.setLine(content);
    }

    public void setLine(String line) {
        this.line = line;
        this.setValid(isFact(line));
        if (this.isValid()) {
            this.setName(line);
            this.setArguments(line);
        }
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    private boolean isFact(String line) {
        line = line.trim();
        return Pattern.matches(FACT_PATTERN,line);
    }

    public void setName(String line) {
        this.name = line.substring(0, line.indexOf('('));
    }

    public void setArguments(String arguments) {
        this.arguments = null;
        String betweenParentheses = arguments.substring(arguments.indexOf('(') + 1, arguments.indexOf(')'));
        if ((betweenParentheses != null) && !(betweenParentheses.isEmpty())) {
            String[] splitedArguments = betweenParentheses.split(",");
            this.arguments = Arrays.asList(splitedArguments);
        }
    }

    public boolean evaluate(Element element) {
        return false;
    }

    public String getLine() {
        return line;
    }


    public String getName() {
        return name;
    }


    public List<String> getArguments() {
        return arguments;
    }

}
