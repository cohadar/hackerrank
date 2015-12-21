import java.util.*;
import java.io.*;
import java.math.BigInteger;

/* Mighty Cohadar */
public class XorAndSum {

	static final int PRIME = (int)1e9 + 7;
	static final int COUNT = 314159;

	static void xorCumul(long[] D, char[] B, boolean[] M) {
		for (int i = B.length - 1, j = 0; i >= 0; i--, j++) {
			if (B[i] == '1') {
				for (int k = 0; k <= COUNT; k++) {
					if (M[k + j]) {
						D[k + j]--;	
					} else {
						D[k + j]++;	
					}
				}
			}
		}		
	}

	static int countOnes(char[] B) {
		int ones = 0;
		for (int i = 0; i < B.length; i++) {
			ones += (B[i] == '1') ? 1 : 0;
		}
		return ones;
	}

	static void xorCumul2(long[] D, char[] B, boolean[] M) {
		int ones = countOnes(B);
		int[] E = new int[B.length + COUNT];
		Arrays.fill(E, ones);
		ones = 0;
		for (int i = B.length - 1; i >= 0; i--) {
			ones += (B[i] == '1') ? 1 : 0;
			E[B.length - 1 - i] = ones;
		}
		ones = 0;
		for (int i = 0; i < B.length; i++) {
			ones += (B[i] == '1') ? 1 : 0;
			E[E.length - 1 - i] = ones;
		}
		debug('E', E);
		for (int k = 0; k < E.length; k++) {
			if (M[k]) {
				D[k] -= E[k];	
			} else {
				D[k] += E[k];	
			}
		}
	}	

	static void stolis() {
		Scanner scanner = new Scanner(System.in);
		char[] A = scanner.nextLine().toCharArray();
		char[] B = scanner.nextLine().toCharArray();
        long result = 0;
        long pow = 1;
        long ones = 0;
        for (int n=0; n<=COUNT; n++) {
            int ca = (A.length > n) ? A[A.length-n-1]-'0' : 0;
            int cb = (B.length > n) ? B[B.length-n-1]-'0' : 0;
            if (cb == 1) {
                ones++;
            }
            result += pow*((ca == 0) ? ones : COUNT+1-ones);
            result %= PRIME;
            pow *= 2;
            pow %= PRIME;
        }
        for (int n=0; n<B.length; n++) {
            if (B[B.length-n-1] == '1') ones--;
            result += ones*pow;
            result %= PRIME;
            pow *= 2;
            pow %= PRIME;
        }
        System.out.println(result);		
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[] A = scanner.nextLine().toCharArray();
		char[] B = scanner.nextLine().toCharArray();
		int MAX_LEN = Math.max(A.length + COUNT + 1, B.length + COUNT + 1);
		long[] D = new long[MAX_LEN];
		boolean[] M = new boolean[MAX_LEN];
		for (int i = A.length - 1, j = 0; i >= 0; i--, j++) {
			if (A[i] == '0') {
				D[j] = 0;
			} else {
				D[j] = COUNT + 1;
				M[j] = true;
			}
		}
		debug(A);
		debug(B);
		debug(M);
		debug(D);
		xorCumul2(D, B, M);
		debug(D);
		long sum = 0;
		long pow = 1;
		for (int i = 0; i < D.length; i++) {
			sum += pow * D[i];
			sum %= PRIME;
			pow *= 2;
			pow %= PRIME;
		}
		System.out.println(sum);
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
