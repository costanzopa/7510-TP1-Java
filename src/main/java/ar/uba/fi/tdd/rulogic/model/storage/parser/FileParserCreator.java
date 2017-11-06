package ar.uba.fi.tdd.rulogic.model.storage.parser;

import java.io.FileNotFoundException;

/**
 * Created by costa on 3/11/2017.
 * FileParser factory implements Factory method pattern
 */
public class FileParserCreator extends ParserCreator {
    private String fileName;

    public FileParserCreator(String fileName){this.setFileName(fileName);}

    @Override
    public Parser createParser() {
        try {
            return new FileParser(this.getFileName());
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
