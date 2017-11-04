package ar.uba.fi.tdd.rulogic.model.storage;

import ar.uba.fi.tdd.rulogic.model.schema.Element;

import java.util.List;

/**
 * Created by costa on 3/11/2017.
 * Add Comments
 */
public interface IDatabase {
    void setInvalid();
    boolean isValid();
    void store(String content);
    String getInvalidLine();
    List<Element> getElements();
    boolean query(String query);

}
