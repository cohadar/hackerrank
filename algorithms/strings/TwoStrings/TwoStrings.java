import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TwoStrings {

	static boolean solve(char[] A, char[] B) {
		int[] ac = new int[256];
		int[] bc = new int[256]; 
		for (int a = 0; a < A.length; a++) {
			ac[A[a]]++;
		}
		for (int b = 0; b < B.length; b++) {
			bc[B[b]]++;
		}
		for (int i = 0; i < 256; i++) {
			if (ac[i] > 0 && bc[i] > 0) {
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
			System.out.println(solve(a.toCharArray(), b.toCharArray()) ? "YES" : "NO");
		}
	}

}

