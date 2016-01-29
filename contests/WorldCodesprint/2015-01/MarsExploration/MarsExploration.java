import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class MarsExploration {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		char[] S = s.toCharArray();
		assert (S.length % 3) == 0;
		int n = S.length / 3; 
		int count = 0;
		for (int i = 0; i < n; i++) {
			int j = 3 * i;
			if (S[j + 0] != 'S') { count++; };
			if (S[j + 1] != 'O') { count++; };
			if (S[j + 2] != 'S') { count++; };
		}
		System.out.println(count);
	}

}

