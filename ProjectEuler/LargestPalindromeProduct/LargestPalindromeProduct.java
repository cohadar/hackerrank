import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class LargestPalindromeProduct {

	static boolean isPalindrome(int n) {
		char[] s = String.valueOf(n).toCharArray();
		for (int i = 0; i < s.length / 2; i++) {
			if (s[i] != s[s.length - i - 1]) {
				return false;
			}
		}
		return true;
	}

	static int solve(int n) {
		int max = 101101;
		for (int a = 999; a >= 100; a--) {
			for (int b = a; b >= 100; b--) {
				if (a * b < n && isPalindrome(a * b)) {
					if (a * b > max) {
						max = a * b;
					}
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			System.out.println(solve(n));
		}
	}

}

