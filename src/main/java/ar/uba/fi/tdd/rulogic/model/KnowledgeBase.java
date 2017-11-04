package ar.uba.fi.tdd.rulogic.model;

import ar.uba.fi.tdd.rulogic.model.storage.IDatabase;
import ar.uba.fi.tdd.rulogic.model.storage.parser.Parser;

public class KnowledgeBase {
	private IDatabase database;
	private Parser parser;

	public KnowledgeBase(Parser parser) {
		this.database = null;
		this.parser = parser;
	}

	public boolean init() {
		boolean valid = false;
		if (parser != null) {
			this.database = parser.parse();
			if (this.database != null) {
				if (!database.isValid()) {
					System.out.println("Init Fail: Invalid Line: " + database.getInvalidLine());
				} else {
					valid = database.isValid();
				}
			}
		}
		return valid;
	}

	public boolean answer(String query) throws Exception {
		return database.query(query);
	}

}
