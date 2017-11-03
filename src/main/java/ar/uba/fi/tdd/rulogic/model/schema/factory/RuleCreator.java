package ar.uba.fi.tdd.rulogic.model.schema.factory;

import ar.uba.fi.tdd.rulogic.model.schema.Element;
import ar.uba.fi.tdd.rulogic.model.schema.Rule;

/**
 * Created by costa on 28/10/2017.
 * Add Comments
 */
public class RuleCreator implements Creator {

    public Element factoryMethod(String content) {
        return new Rule(content);
    }
}
