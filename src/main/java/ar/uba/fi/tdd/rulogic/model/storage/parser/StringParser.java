package ar.uba.fi.tdd.rulogic.model.storage.parser;


import ar.uba.fi.tdd.rulogic.model.storage.Database;
import ar.uba.fi.tdd.rulogic.model.storage.IDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by costa on 27/10/2017.
 * Add Comments
 */
public class StringParser implements Parser {
    private String string;

    public StringParser(String content) {
        this.setString(content);
    }

    @Override
    public IDatabase parse() {
        IDatabase database = new Database();
        String databaseLine;
        BufferedReader reader = new BufferedReader(new StringReader(this.getString()));
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

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
