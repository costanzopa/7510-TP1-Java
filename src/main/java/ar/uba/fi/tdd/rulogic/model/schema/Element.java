package ar.uba.fi.tdd.rulogic.model.schema;

import java.util.List;

/**
 * Created by costa on 28/10/2017.
 * Element Interface.
 */
public interface Element {

    boolean evaluate(Element element);
    boolean isValid();

    String getName();

    List<String> getArguments();

    List<Element> getRightSide();
}
