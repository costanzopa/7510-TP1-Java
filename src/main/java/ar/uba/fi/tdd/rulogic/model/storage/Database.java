package ar.uba.fi.tdd.rulogic.model.storage;

import ar.uba.fi.tdd.rulogic.model.schema.Element;
import ar.uba.fi.tdd.rulogic.model.schema.Fact;
import ar.uba.fi.tdd.rulogic.model.schema.factory.Creator;
import ar.uba.fi.tdd.rulogic.model.schema.factory.FactCreator;
import ar.uba.fi.tdd.rulogic.model.schema.factory.RuleCreator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by costa on 24/10/2017.
 * Add Comments
 */
public class  Database implements IDatabase {
    private List<Element> elements = null;
    private String lineInvalid;
    private boolean valid;

    public Database() {
        this.elements = new ArrayList<>();
        this.lineInvalid = "";
        this.valid = true;
    }


    public void setInvalid() { this.valid = false;}
    public boolean isValid() {return valid;}

    private void setLineInvalid(String lineInvalid) {
        this.lineInvalid = lineInvalid;
    }
    public String getInvalidLine() {return this.lineInvalid;}

    @Override
    public List<Element> getElements() {
        return elements;
    }

    @Override
    public boolean query(String query) {
        boolean answer = false;
        Creator creator = new FactCreator();
        Element queryElement = creator.factoryMethod(query);
        if (queryElement.isValid()) {
            answer = evaluate(queryElement);
        }
        return answer;
    }

    private boolean evaluate(Element queryElement) {
        Iterator<Element> iterator = this.getElements().iterator();

        while (iterator.hasNext()) {
            Element element = iterator.next();
            if (element.evaluate(queryElement)){
                if (element.getRightSide() == null) {
                    return true;
                } else {
                    Iterator<Element> rightSide = element.getRightSide().iterator();
                    while (rightSide.hasNext()) {
                        Element fact = iterator.next();
                        Element newQueryElement = combineArguments(element.getArguments(),queryElement.getArguments(),fact);
                        if (!evaluate(newQueryElement)) {
                           return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private Element combineArguments(List<String> arguments, List<String> queryElementArguments, Element fact) {
        String line = fact.getLine();
        Iterator<String> iteratorToBeReplace = arguments.iterator();
        Iterator<String> iteratorToReplace =queryElementArguments.iterator();
       while (iteratorToBeReplace.hasNext() && iteratorToReplace.hasNext() ) {
           String toBeReplace = iteratorToBeReplace.next();
           String toReplace = iteratorToReplace.next();
           line = line.replaceAll(toBeReplace,toReplace);
       }
        return new Fact(line);
    }

    private void add(Element element) {
        this.elements.add(element);
    }

    public void store(String databaseLine) {
        Creator creator;
        if (databaseLine.contains(":-")) {
            creator = new RuleCreator();
        } else {
            creator = new FactCreator();
        }
        Element element = creator.factoryMethod(databaseLine);
        if (element.isValid()) {
            this.add(element);
        } else {
            this.setInvalid();
            this.setLineInvalid(databaseLine);
        }
    }

}
