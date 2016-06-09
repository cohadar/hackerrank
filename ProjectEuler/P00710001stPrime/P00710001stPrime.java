import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class P00710001stPrime {

	public static boolean[] sieve(int n) {
		boolean[] P = new boolean[n];
		P[2] = true;
		for (int i = 3; i < n; i+=2) {
			P[i] = true;
		}
		for (int i = 3; i * i <= n; i+=2) {
			if (P[i]) {
				for (int j = i * i; j < n; j += i) {
					P[j] = false;
				}
			}
		}
		return P;
	}

	public static List<Integer> primes(int n) {
		boolean[] P = sieve(n);
		List<Integer> R = new ArrayList<>();
		for (int i = 0; i < P.length; i++) {
			if (P[i]) {
				R.add(i);	
			}			
		}
		return R;
	}

	public static void main(String[] args) {
		List<Integer> primes = primes(200_000);
		Scanner scanner = new Scanner(System.in);
		int tt = scanner.nextInt();
		assert 1 <= tt && tt <= 1e3 : "out of range, tt: " + tt;
		for (int it = 0; it < tt; it++) {
			int nn = scanner.nextInt();
			assert 1 <= nn && nn <= 1e4 : "out of range, nn: " + nn;
			System.out.println(primes.get(nn - 1));			
		}
	}

}


