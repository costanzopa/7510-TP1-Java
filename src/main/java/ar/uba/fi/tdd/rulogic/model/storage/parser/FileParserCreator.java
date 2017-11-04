package ar.uba.fi.tdd.rulogic.model.storage.parser;

import java.io.FileNotFoundException;

/**
 * Created by costa on 3/11/2017.
 * Add Comments
 */
public class FileParserCreator extends ParserCreator {
    private String fileName;

    public FileParserCreator(String fileName){this.setFileName(fileName);}

    @Override
    public Parser createParser() {
        try {
            return new FileParser(this.getFileName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
