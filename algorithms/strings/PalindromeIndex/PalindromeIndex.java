import java.util.*;
import java.io.*;

public class PalindromeIndex {

	static boolean isPalindrome(char[] s, int l, int r) {
		while (l < r) { 
			if (s[l] == s[r]) {
				l++;
				r--;
			} else {
				return false;
			}
		}
		return true;
	}

	static int solve(char[] s) {
		int l = 0;
		int r = s.length - 1;
		while (l < r) {
			if (s[l] == s[r]) {
				l++;
				r--;
			} else {
				if (isPalindrome(s, l + 1, r)) {
					return l;
				} else {
					return r;
				}
			}
		}
		return -1;
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

