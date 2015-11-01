import java.util.*;
import java.io.*;

public class TheLoveLetterMistery {

	static int solve(char[] s) {
		int n = s.length;
		int sum = 0;
		for (int i = 0; i < n / 2; i++) {
			sum += Math.abs(s[i] - s[n - i - 1]);
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String s = scanner.nextLine();
			System.out.println(solve(s.toCharArray()));
		}
	}

}
