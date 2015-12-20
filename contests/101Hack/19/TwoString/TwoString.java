import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TwoString {

	static boolean solve(char[] A, char[] B) {
		boolean[] C = new boolean[256];
		for (char a : A) {
			C[a] = true;
		}
		for (char b : B) {
			if (C[b]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String a = scanner.nextLine();
			String b = scanner.nextLine();
			System.out.println((solve(a.toCharArray(), b.toCharArray())) ? "YES" : "NO");
		}
	}

}

