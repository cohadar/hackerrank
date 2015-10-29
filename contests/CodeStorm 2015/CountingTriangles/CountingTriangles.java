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

	// a lot of this algorithm is based on the fact that array elements are unique.
	void count(int ia, int ib) {
		int a = S[ia];
		int b = S[ib];
		int lo = ib + 1;
		int hi = Arrays.binarySearch(S, lo, S.length, a + b);
		if (hi < 0) { 
			hi = -hi - 1; 
		}
		assert S[hi - 1] < a + b;
		int dd = a * a + b * b;
		int md = Arrays.binarySearch(S2, lo, hi, dd);
		if (md < 0) {
			md = -md - 1;
			obtuse += hi - md;
			acute += md - lo;
		} else {
			right += 1;
			obtuse += hi - md - 1;
			assert hi - md - 1 >= 0;
			acute += md - lo;
		}
		assert lo <= md;
		assert md <= hi;
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

