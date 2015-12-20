import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class AnuragAndMaths {

	static int[] freq(char[] N) {
		int[] f = new int[10];
		for (char c : N) {
			f[c - '0']++;
		}
		return f;
	}

	static List<int[]> freqList() {
		List<int[]> F = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			if (i % 8 == 0) {
				F.add(freq(String.format("%03d", i).toCharArray()));
			}
		}
		return F;
	}

	static List<int[]> freqList2() {
		List<int[]> F = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			if (i % 8 == 0) {
				F.add(freq(String.valueOf(i).toCharArray()));
			}
		}
		return F;
	}	

	static boolean strictlyLess(int[] a, int[] b) {
		if (a[0] > b[0]) {
			return false;
		}
		int delta = 0;
		for (int i = 1; i < 10; i++) {
			if (a[i] > b[i]) {
				return false;
			} else {
				delta += (b[i] - a[i]);
			}
		}
		return delta > 0;
	}

	static boolean divisibleBy8(String s, List<int[]> F, List<int[]> H) {
		int[] g = freq(s.toCharArray());
		for (int[] f : F) {
			if (strictlyLess(f, g)) {
				return true;
			}
		}
		for (int[] h : H) {
			if (Arrays.equals(h, g)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<int[]> F = freqList();
		List<int[]> H = freqList2();
		StringBuilder sb = new StringBuilder();
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String s = scanner.nextLine();
			sb.append((divisibleBy8(s, F, H)) ? "YES\n" : "NO\n");
		}
		System.out.println(sb);
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}

