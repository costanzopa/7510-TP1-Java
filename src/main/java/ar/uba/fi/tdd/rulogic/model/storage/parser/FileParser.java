package ar.uba.fi.tdd.rulogic.model.storage.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by costa on 3/11/2017.
 * Add Comments
 */
public class FileParser extends Parser{
    public FileParser(String fileName) throws FileNotFoundException {
        this.setContent(new FileReader(fileName));
    }
}
