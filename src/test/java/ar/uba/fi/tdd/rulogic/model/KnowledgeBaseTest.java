package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import ar.uba.fi.tdd.rulogic.model.storage.parser.FileParser;
import ar.uba.fi.tdd.rulogic.model.storage.parser.FileParserCreator;
import ar.uba.fi.tdd.rulogic.model.storage.parser.ParserCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KnowledgeBaseTest {

	private KnowledgeBase knowledgeBase;
	private String rule_db = "src/main/resources/rules.db";
	private String incomplete_database = "src/main/resources/incomplete_database.db";
	private String parentDatabase = "src/main/resources/parent.db";
	private String numberDatabase = "src/main/resources/number.db";

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void invalidFilePathDatabaseTest() throws Exception {
		ParserCreator creator = new FileParserCreator("");
		knowledgeBase = new KnowledgeBase(creator.createParser());

		Assert.assertFalse(knowledgeBase.init());
	}

	@Test
	public void incompleteDatabaseFactTest() throws Exception {
		knowledgeBase = new KnowledgeBase(new FileParser(incomplete_database));

		Assert.assertFalse(knowledgeBase.init());
	}

	@Test(expected = Exception.class)
	public void testIndexOutOfBoundsException() throws Exception {
		ParserCreator creator = new FileParserCreator(rule_db);
		knowledgeBase = new KnowledgeBase(creator.createParser());

		Assert.assertTrue(knowledgeBase.init());
		knowledgeBase.answer("varon(");
	}

	@Test
	public void numberDatabaseFactTest() throws Exception {
		ParserCreator creator = new FileParserCreator(numberDatabase);
		knowledgeBase = new KnowledgeBase(creator.createParser());
		knowledgeBase.init();
		Assert.assertTrue(this.knowledgeBase.answer("add(one, one, two)"));
	}

	@Test
	public void numberDatabaseFalseFactTest() throws Exception {
		ParserCreator creator = new FileParserCreator(numberDatabase);
		knowledgeBase = new KnowledgeBase(creator.createParser());
		knowledgeBase.init();
		Assert.assertFalse(this.knowledgeBase.answer("add(two, one, one)"));
	}

	@Test
	public void test() throws Exception {
		knowledgeBase = new KnowledgeBase(new FileParser(rule_db));
		knowledgeBase.init();
		Assert.assertFalse(this.knowledgeBase.answer("varon (javier)."));
	}



}
