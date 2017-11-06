package ar.uba.fi.tdd.rulogic.model.storage.parser;

import java.io.StringReader;

/**
 * Created by costa on 27/10/2017.
 * Add Comments
 */
public class StringParser extends Parser {

    public StringParser(String content) {
        this.setContent(new StringReader(content));
    }
}
