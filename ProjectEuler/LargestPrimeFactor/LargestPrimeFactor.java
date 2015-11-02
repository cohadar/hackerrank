import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class LargestPrimeFactor {

	static long largestPrime(long n) {
		long lp = 1;		
		for (long i = 2; i * i <= n; i++) {
			while (n % i == 0) {
				lp = i;
				n /= i;
			}
		}
		// return Math.max(lp, n);
		return (n > 1) ? n : lp;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			long l = scanner.nextLong();
			System.out.println(largestPrime(l));
		}
	}

}
