package ar.uba.fi.tdd.rulogic.model.storage;

import ar.uba.fi.tdd.rulogic.model.schema.Element;
import ar.uba.fi.tdd.rulogic.model.schema.Fact;
import ar.uba.fi.tdd.rulogic.model.schema.Rule;
import ar.uba.fi.tdd.rulogic.model.storage.parser.Parser;
import ar.uba.fi.tdd.rulogic.model.storage.parser.ParserCreator;
import ar.uba.fi.tdd.rulogic.model.storage.parser.StringParserCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by costa on 2/11/2017.
 * Add Comments
 */


public class DatabaseTest {

    private Parser parser;
    private ParserCreator parserCreator;

    @Mock
    private IDatabase database;

    private String StringDatabase = "varon(juan).\n" +
            "varon(pepe).\n" +
            "varon(hector).\n" +
            "varon(roberto).\n" +
            "varon(alejandro).\n" +
            "mujer(maria) .\n" +
            "mujer(cecilia).\n" +
            "padre(juan, pepe).\n" +
            "padre(juan, pepa).\n" +
            "padre(hector, maria).\n" +
            "padre(roberto, alejandro).\n" +
            "padre(roberto, cecilia).\n" +
            "hijo(X, Y) :- varon(X), padre(Y, X).\n" +
            "hija(X, Y) :- mujer(X), padre(Y, X).\n" +
            "hermano(nicolas, roberto).\n" +
            "hermano(roberto, nicolas).\n" +
            "varon ( nicolas ) .\n" +
            "tio(X, Y, Z):- varon(X),\thermano(X, Z),padre(Z, Y).\n" +
            "tia(X, Y, Z):- mujer(X),\thermano(X, Z),padre(Z, Y).";


    private String StringIncompleteDatabase = "varon(juan).\n" +
            "\tvaron\n" +
            "varon ( nicolas ) .";

    private String StringSubtractDatabase = "\tadd(two, one, zero).\n" +
            "\tadd(two, two, one).\n" +
            "\tsubtract(X, Y, Z) :- add(Y, Z, X).";

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void stringParseDatabaseIsValidTest() {
        parserCreator = new StringParserCreator(StringDatabase);
        parser = parserCreator.createParser();

        database = parser.parse();

        Assert.assertTrue(database.isValid());
    }


    @Test
    public void stringParseDatabaseIsInValidTest() {
        parserCreator = new StringParserCreator(StringIncompleteDatabase);
        parser = parserCreator.createParser();

        database = parser.parse();

        Assert.assertFalse(database.isValid());
    }


    @Test
    public void stringParseDatabaseIsInValidGetLineTest() {
        when(database.getInvalidLine()).thenReturn("\tvaron");
        parserCreator = new StringParserCreator(StringIncompleteDatabase);
        parser = parserCreator.createParser();

        IDatabase databaseTest = parser.parse();
        Assert.assertEquals(databaseTest.getInvalidLine(),database.getInvalidLine());
    }

    @Test
    public void stringParseDatabaseGetElemetsTest() {
        List<Element> arguments = new ArrayList<Element>() {{add(new Fact("add(two, one, zero)."));
                                                             add(new Fact("add(two, two, one)."));
                                                             add(new Rule("subtract(X, Y, Z) :- add(Y, Z, X)."));}};
        when(database.getElements()).thenReturn(arguments);
        parserCreator = new StringParserCreator(StringSubtractDatabase);
        parser = parserCreator.createParser();

        IDatabase databaseTest = parser.parse();
        Assert.assertEquals(databaseTest.getElements().get(0).getName(),database.getElements().get(0).getName());
        Assert.assertEquals(databaseTest.getElements().get(1).getName(),database.getElements().get(1).getName());
        Assert.assertEquals(databaseTest.getElements().get(2).getName(),database.getElements().get(2).getName());
    }
}
