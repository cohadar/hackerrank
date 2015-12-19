import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Pangrams {

	static boolean pangram(String s) {
		for (int i = 'a'; i <= 'z'; i++) {
			if (s.indexOf(i) < 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		System.out.println((pangram(s.toLowerCase())) ? "pangram" : "not pangram");
	}

}

