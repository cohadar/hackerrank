import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class RatAndNikhil {

	static boolean contains(char[] T, char[] P) {
		if (P.length > T.length) {
			return false;
		}
		long[] CT = new long[T.length - P.length + 1];
		long sumP = 0;
		long sumT = 0;
		for (int i = 0; i < P.length; i++) {
			sumP += P[i];
			sumT += T[i];
		}
		CT[0] = sumT;
		for (int i = 1; i < CT.length; i++) {
			CT[i] = CT[i - 1] - T[i - 1] + T[i + P.length - 1];
		}
		Arrays.sort(P);
		for (int i = 0; i < CT.length; i++) {
			if (CT[i] == sumP) {
				char[] Q = Arrays.copyOfRange(T, i, i + P.length);
				Arrays.sort(Q);
				if (Arrays.equals(P, Q)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String pattern = scanner.nextLine();
			String text = scanner.nextLine();
			sb.append((contains(text.toCharArray(), pattern.toCharArray())) ? "YES\n" : "NO\n");
		}
		System.out.println(sb);
	}

}

