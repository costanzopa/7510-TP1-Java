package ar.uba.fi.tdd.rulogic.model.schema.factory;

import ar.uba.fi.tdd.rulogic.model.schema.Element;
import ar.uba.fi.tdd.rulogic.model.schema.Fact;

/**
 * Created by costa on 2/11/2017.
 * Add Comments
 */
public class FactCreator implements Creator{
    @Override
    public Element factoryMethod(String content) {
        return new Fact(content);
    }
}
