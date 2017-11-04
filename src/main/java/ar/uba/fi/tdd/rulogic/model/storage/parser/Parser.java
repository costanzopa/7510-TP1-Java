package ar.uba.fi.tdd.rulogic.model.storage.parser;

import ar.uba.fi.tdd.rulogic.model.storage.Database;
import ar.uba.fi.tdd.rulogic.model.storage.IDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by costa on 27/10/2017.
 * Add Comments
 */
public abstract class Parser {
    private Reader reader;

    public IDatabase parse() {
        IDatabase database = new Database();
        String databaseLine;
        BufferedReader reader = new BufferedReader(this.getContent());
        try {
            while ((databaseLine = reader.readLine()) != null) {
                if (!database.isValid()) {
                    break;
                }
                database.store(databaseLine);
            }
        } catch(IOException e) {
            database.setInvalid();
            return database;
        }
        return database;
    }

    public Reader getContent() {return reader;}
    public void setContent(Reader reader) {this.reader = reader;}
}
