import java.util.*;
import java.io.*;
import java.util.regex.*;

/* Mighty Cohadar */
public class HackerRankLanguage {

	static final String[] LANGS = "C:CPP:JAVA:PYTHON:PERL:PHP:RUBY:CSHARP:HASKELL:CLOJURE:BASH:SCALA:ERLANG:CLISP:LUA:BRAINFUCK:JAVASCRIPT:GO:D:OCAML:R:PASCAL:SBCL:DART:GROOVY:OBJECTIVEC".split(":");

	static final Pattern PATTERN = Pattern.compile("[1-9][0-9]{4} (.*)");

	static boolean validLanguage(String lang) {
		for (String l : LANGS) {
			if (l.equals(lang)) {
				return true;
			}
		}
		return false;
	}

	static boolean isValid(String s) {
		Matcher m = PATTERN.matcher(s);
		if (!m.matches()) {
			return false;	
		}
		return validLanguage(m.group(1));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String s = scanner.nextLine();
			System.out.println((isValid(s)) ? "VALID" : "INVALID");
		}
	}

}

