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

	static class Pair implements Comparable<Pair> {
		final int ic;
		final int id;
		Pair(int ic, int id) {
			this.ic = ic;
			this.id = id;
		}
		public int compareTo(Pair that) {
			if (this.ic == that.ic) {
				return -Integer.compare(this.id, that.id);
			} else {
				return -Integer.compare(this.ic, that.ic);
			}
		}
		public String toString() {
			return String.format("(ic=%d, id=%d)", ic, id);
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

	static long solveA3BCD(int ia, Sticks[] S, Map<Integer, Integer> H, Map<Integer, List<Pair>> P) {
		long count = 0;
		int a = S[ia].length;
		for (int ib = ia - 1; ib >= 0; ib--) {
			int b = S[ib].length;
			int c = a - 2 * b;
			Integer ic = H.get(c);
			if (ic != null) {
				if (ib == ic) {
					count += binom3(S[ib].count);	
				} else {
					count += binom2(S[ib].count) * S[ic].count;
				}
			}	
			int cd = a - b;
			List<Pair> lp = P.get(cd);
			if (lp != null) {
				for (Pair p : lp) {
					if (ib < p.ic) {
						count += S[ib].count * S[p.ic].count * S[p.id].count;
					} else {
						break;
					}
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

	static long solve(int[] A) {
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
		Map<Integer, List<Pair>> P = new HashMap<>(); // c.length + d.length -> (ic, id)
		for (int ic = 0; ic < S.length - 1; ic++) {
			for (int id = ic + 1; id < S.length; id++) {
				int cd = S[ic].length + S[id].length;
				List<Pair> lp = P.get(cd);
				if (lp == null) {
					lp = new ArrayList<>();
					P.put(cd, lp);
				}
				lp.add(new Pair(ic, id));
			}
		}
		for (List<Pair> lp : P.values()) {
			Collections.sort(lp);
		}
		for (int ia = S.length - 1; ia >= 0; ia--) {
			if (S[ia].count >= 3) {
				countA3 += binom3(S[ia].count) * solveA3BCD(ia, S, H, P);
			}
			if (S[ia].count >= 2) {
				countA2 += binom2(S[ia].count) * solveA2BCDE(ia, S, H);
			}
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

