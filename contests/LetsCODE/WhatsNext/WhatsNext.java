import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class WhatsNext {

	static final int INF = Integer.MAX_VALUE;

	static void swap(char[] A, int i, int j) {
		char t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	static String next(char[] C) {
		for (int i = C.length - 1; i >= 1; i--) {
			if (C[i] > C[i - 1]) {
				int min = C[i];  // min previous bigger than C[i - 1]
				int mini = i;
				for (int j = i + 1; j < C.length; j++) {
					if (C[j] > C[i - 1] && min > C[j]) {
						min = C[j];
						mini = j;
					}
				}
				swap(C, i - 1, mini);
				Arrays.sort(C, i, C.length);
				return new String(C);
			}
		}
		return "no answer";
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String s = scanner.nextLine();
			sb.append(next(s.toCharArray()));
			sb.append('\n');
		}
		System.out.println(sb);
	}

}

