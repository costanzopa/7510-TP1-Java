package ar.uba.fi.tdd.rulogic.model.storage;

import ar.uba.fi.tdd.rulogic.model.schema.Element;
import ar.uba.fi.tdd.rulogic.model.schema.factory.Creator;
import ar.uba.fi.tdd.rulogic.model.schema.factory.FactCreator;
import ar.uba.fi.tdd.rulogic.model.schema.factory.RuleCreator;

import java.util.ArrayList;
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
