package ar.uba.fi.tdd.rulogic.model.schema;

/**
 * Created by costa on 28/10/2017.
 */
public interface Element {
    boolean isValid();
    boolean evaluate(Element element);
}
