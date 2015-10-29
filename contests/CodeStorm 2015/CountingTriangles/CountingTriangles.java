import java.util.*;
import java.io.*;

public class CountingTriangles {

	int[] S;
	int[] S2;
	long acute;
	long right;
	long obtuse;

	CountingTriangles(int[] S) {
		Arrays.sort(S);
		this.S = S;
		S2 = new int[S.length];
		for (int i = 0; i < S.length; i++) {
			S2[i] = S[i] * S[i];
		}
	}

	void count(int ia, int ib) {
		int a = S[ia];
		int b = S[ib];
		int lo = Arrays.binarySearch(S, ib + 1, S.length, b);
		if (lo < 0) { lo = -lo - 1; };
		if (lo >= S.length) return;
		int hi = Arrays.binarySearch(S, lo, S.length, a + b + 1);
		if (hi < 0) {
			hi = -hi - 1;
		}
		if (hi > S.length) {
			hi = S.length;
		}
		while (S[hi - 1] >= a + b) hi--;

		int md = Arrays.binarySearch(S2, lo, hi, a * a + b * b);
		if (md < 0) {
			md = -md - 1;
			obtuse += hi - md;
			acute += md - lo;
		} else {
			right += 1;
			obtuse += hi - md - 1;
			acute += md - lo;
		}
		assert lo <= md;
		assert md <= hi;


		// int dd = a * a + b * b;
		// for (int i = lo; i < hi; i++) {
		// 	int c = S[i];
		// 	int cc = c * c;
		// 	if (cc < dd) {
		// 		acute++;
		// 	} else if (cc > dd) {
		// 		obtuse++;
		// 	} else {
		// 		right++;
		// 	}			
		// }
	}

	void solve() { 
		for (int ia = 0; ia < S.length - 2; ia++) {
			for (int ib = ia + 1; ib < S.length - 1; ib++) {
				count(ia, ib);
			}
		}
	}

	static void scan(Scanner scanner) {
		int n = Integer.valueOf(scanner.nextLine());
		int[] S = new int[n];
		for (int i = 0; i < n; i++) {
			S[i] = scanner.nextInt();
		}
		CountingTriangles ct = new CountingTriangles(S);
		ct.solve();
		System.out.printf("%d %d %d\n", ct.acute, ct.right, ct.obtuse);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("CountingTriangles.in"));
		}
		scan(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

