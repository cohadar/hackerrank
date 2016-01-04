import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class NewYearPresent {

	static class Sticks {
		final int length;
		final int count;
		Sticks(int length, int count) {
			this.length = length;
			this.count = count;
		}
		public String toString() {
			return String.format("(length=%d, count=%d)", length, count);
		}	
	}

	static long binom2(long n) {
		if (n < 2) { return 0; };
		return n * (n - 1) / 2;
	}

	static long binom3(long n) {
		if (n < 3) { return 0; };
		return n * (n - 1) * (n - 2) / (3 * 2);
	}	

	static long binom4(long n) {
		if (n < 4) { return 0; };
		return n * (n - 1) * (n - 2) * (n - 3) / (4 * 3 * 2);
	}	

	static long solveBCD(int[] A, int ia3, int a) {
		long count = 0;
		for (int ib = ia3 - 1; ib >= 2; ib--) {
			for (int ic = ib - 1; ic >= 1; ic--) {
				for (int id = ic - 1; id >= 0; id--) {
					if (A[ib] + A[ic] + A[id] == a) {
						count++;
					}
				}
			}
		}
		return count;
	}

	static long solveA3(int[] A, int ia2, int a) {
		long count = 0;
		for (int ia3 = ia2 - 1; (ia3 >= 3) && (A[ia2] == A[ia3]); ia3--) {
			count += solveBCD(A, ia3, a);
		}
		return count;
	}

	static boolean ok22(int a, int b, int c, int d, int e) {
		return (b + c == a) && (d + e == a);
	}

	static boolean ok4(int a, int b, int c, int d, int e) {
		if (ok22(a, b, c, d, e) || ok22(a, b, d, c, e) || ok22(a, b, e, d, c)) {
			return true;
		}
		return false;
	}

	static long solveA2(int[] A, int ia2, int a) {
		long count = 0;
		for (int ib = ia2 - 1; ib >= 3; ib--) {
			for (int ic = ib - 1; ic >= 2; ic--) {
				for (int id = ic - 1; id >= 1; id--) {
					for (int ie = id - 1; ie >= 0; ie--) {
						if (ok4(a, A[ib], A[ic], A[id], A[ie])) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	static long solve(int[] A) {
		long countA3 = 0;
		long countA2 = 0;
		Arrays.sort(A);
		for (int ia = A.length - 1; ia >= 5; ia--) {
			for (int ia2 = ia - 1; (ia2 >= 4) && (A[ia] == A[ia2]); ia2--) {
				countA3 += solveA3(A, ia2, A[ia]);
				countA2 += solveA2(A, ia2, A[ia]);
			}
		}
		debug(countA3, countA2);
		return countA3 + countA2;
	}

	static long pick2(Sticks[] S, int ib, int ic) {
		int b = S[ib].count;
		int c = S[ic].count;
		if (ib == ic) {
			return binom2(b);
		} else {
			return b * c;
		}
	}

	static long pick4(Sticks[] S, int ib, int ic) {
		int b = S[ib].count;
		int c = S[ic].count;
		if (ib == ic) {
			return binom4(b);
		} else {
			return binom2(b) * binom2(c);
		}
	}

	static long solveA3BCD(int ia, Sticks[] S, Map<Integer, Integer> H) {
		long count = 0;
		int a = S[ia].length;
		for (int ib = ia - 1; ib >= 0; ib--) {
			int b = S[ib].length;
			if (3 * b < a) {
				break;
			}
			if (3 * b == a) {
				count += binom3(S[ib].count);
			}
			for (int ic = ib - 1; ic >= 0; ic--) {
				int c = S[ic].length;
				if (b + c >= a) { continue; };
				if (2 * b + c == a) {
					count += binom2(S[ib].count) * S[ic].count;
				}
				if (b + 2 * c == a) {
					count += S[ib].count * binom2(S[ic].count);
				}
				int d = a - (b + c);
				if (c <= d) { break; };				
				Integer id = H.get(d);
				if (id != null) {
					count += S[ib].count * S[ic].count * S[id].count;
				}
			}
		}
		return count;
	}

	static long solveA2BCDE(int ia, Sticks[] S, Map<Integer, Integer> H) {
		long count = 0;
		long prevSum = 0;
		int a = S[ia].length;
		for (int ib = 0; ib < ia && S[ib].length <= a / 2; ib++) {
			Integer ic = H.get(a - S[ib].length);
			if (ic == null) { continue; };
			count += pick4(S, ib, ic);
			long p2 = pick2(S, ib, ic);
			count += prevSum * p2;
			prevSum += p2;
		}
		return count;
	}	

	static long solve2(int[] A) {
		long countA3 = 0;
		long countA2 = 0;
		SortedMap<Integer, Integer> M = new TreeMap<Integer, Integer>();
		for (int i = 0; i < A.length; i++) {
			Integer v = M.get(A[i]);
			if (v == null) { v = 0; };
			M.put(A[i], v + 1);
		}
		Sticks[] S = new Sticks[M.size()];
		Map<Integer, Integer> H = new HashMap<>(); // length -> S index
		int j = 0;
		for (Map.Entry<Integer, Integer> e : M.entrySet()) {
			S[j] = new Sticks(e.getKey(), e.getValue());
			H.put(e.getKey(), j);
			j++;
		}
		for (int ia = S.length - 1; ia >= 0; ia--) {
			countA3 += binom3(S[ia].count) * solveA3BCD(ia, S, H);
			countA2 += binom2(S[ia].count) * solveA2BCDE(ia, S, H);
		}
		debug(countA3, countA2);
		return countA3 + countA2;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 6 <= n && n <= 3000 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		System.out.println(solve(A));
		System.out.println(solve2(A));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

