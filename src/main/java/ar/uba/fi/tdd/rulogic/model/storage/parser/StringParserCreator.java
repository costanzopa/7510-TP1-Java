package ar.uba.fi.tdd.rulogic.model.storage.parser;

/**
 * Created by costa on 27/10/2017.
 * Add Comments
 */
public class StringParserCreator extends ParserCreator{
    private String content;

    public StringParserCreator(String content) {
        this.setContent(content);
    }


    @Override
    public Parser createParser() {

        return new StringParser(this.getContent());
    }

    private String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }
}
