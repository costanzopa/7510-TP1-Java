package ar.uba.fi.tdd.rulogic.model.schema.factory;

import ar.uba.fi.tdd.rulogic.model.schema.Element;

/**
 * Created by costa on 28/10/2017.
 * Add Comments
 */

public interface  Creator{
    Element factoryMethod(String content);
}
