import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class StraightFlush {
									   //:;<=>
	static final String CARD = "123456789TJQKA";

	static boolean suitesSame(char[] suites) {
		char c = suites[0];
		for (char d : suites) {
			if (c != d) {
				return false;
			}
		}
		return true;
	}

	static boolean flush(String cards) {
		int[] A = new int[5];
		for (int i = 0; i < 5; i++) {
			A[i] = CARD.indexOf(cards.charAt(i));
		}
		Arrays.sort(A);
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] + 1 != A[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String cards = "";
		String suites = "";
		for (int i = 0; i < 5; i++) {
			String l = scanner.nextLine();
			cards += l.charAt(0);
			suites += l.charAt(1);
		}
		if (!suitesSame(suites.toCharArray())) {
			System.out.println("NO");
			return;
		}
		if (flush(cards) || flush(cards.replace("A", "1"))) {
			System.out.println("YES");
			return;
		}
		System.out.println("NO");
	}

}

