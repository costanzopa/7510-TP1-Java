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

	public static void main(String[] args) throws Exception {
		KnowledgeBase knowledgeBase;
		Parser parser;
		ParserCreator parserCreator;
        System.out.println(args[0]);
		if (args.length == 2) {
			if (args[0].equals("-f")) {
			    System.out.println("Tiene Dos argumentos.");
				parserCreator = new FileParserCreator(args[1]);
				parser = parserCreator.createParser();
				knowledgeBase = new KnowledgeBase(parser);
			} else {
				printHelp();
				return;
			}

		} else if (args.length == 1) {
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
				System.out.print("Enter query:");
				String input = inputReader.nextLine();
				if (input != null && input.equalsIgnoreCase("-q")) {
                    break;
                }

				String answer;
				if (knowledgeBase.answer(input)) {
					answer = "SI";
				} else {
					answer = "NO";
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
