package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import ar.uba.fi.tdd.rulogic.model.storage.parser.FileParserCreator;
import ar.uba.fi.tdd.rulogic.model.storage.parser.Parser;
import ar.uba.fi.tdd.rulogic.model.storage.parser.ParserCreator;
import ar.uba.fi.tdd.rulogic.model.storage.parser.StringParserCreator;

import java.util.Scanner;

/**
 * Console application.
 *
 */
public class App
{

	private static final int NUMBER_OF_FILE_ARGUMENTS = 2;
	private static final String FILE_ARGUMENT = "-f";
	private static final int NUMBER_OF_STRING_ARGUMENTS = 1;
	private static final String QUIT_PROGRAM = "-q";
	private static final String TRUE_ANSWER = "SI";
	private static final String FALSE_ANSWER = "NO";


	public static void main(String[] args) throws Exception {
		KnowledgeBase knowledgeBase;
		Parser parser;
		ParserCreator parserCreator;
		if (args.length == NUMBER_OF_FILE_ARGUMENTS) {
			if (args[0].equals(FILE_ARGUMENT)) {
				parserCreator = new FileParserCreator(args[1]);
				parser = parserCreator.createParser();
				knowledgeBase = new KnowledgeBase(parser);
			} else {
				printHelp();
				return;
			}

		} else if (args.length == NUMBER_OF_STRING_ARGUMENTS) {
			parserCreator = new StringParserCreator(args[0]);
			parser = parserCreator.createParser();
			knowledgeBase = new KnowledgeBase(parser);
		} else {
			printHelp();
			return;
		}

		if (knowledgeBase.init()) {
            Scanner inputReader = new Scanner(System.in);
			while (true) {
				System.out.print("Enter query (-q to quit the program): ");
				String input = inputReader.nextLine();
				if (input != null && input.equalsIgnoreCase(QUIT_PROGRAM)) {
                    break;
                }

				String answer;
				if (knowledgeBase.answer(input)) {
					answer = TRUE_ANSWER;
				} else {
					answer = FALSE_ANSWER;
				}
				System.out.println(input + " --> " + answer);
			}
		}

    }

    private static void printHelp() {
		System.out.print("./main -f [filepath]");
		System.out.print("./main [StringDatabase]");
	}
}
