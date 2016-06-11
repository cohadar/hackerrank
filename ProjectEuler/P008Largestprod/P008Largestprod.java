import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class P008Largestprod {

	final int nn;
	final int kk;
	final char[] C;
	
	public P008Largestprod(int nn, int kk, char[] C) {
		this.nn = nn;
		this.kk = kk;
		this.C = C;
	}

	public int solve() {
		int max = Integer.MIN_VALUE;
		for (int l = 0; l <= nn - kk; l++) {
			int p = 1;
			for (int i = 0; i < kk; i++) {
				p *= (C[l + i] - '0');
			}
			max = Math.max(max, p);
		}
		return max;
	}

	public static P008Largestprod load(Scanner scanner) {
		int nn = scanner.nextInt();
		int kk = scanner.nextInt();
		scanner.nextLine();
		char[] C = scanner.nextLine().toCharArray();
		assert 1 <= kk && kk <= 7 : "out of range, kk: " + kk;
		assert kk <= nn && nn <= 1000 : "out of range, nn: " + nn;
		return new P008Largestprod(nn, kk, C);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 100 : "out of range, t: " + t;
		while (t-->0) {
			P008Largestprod o = P008Largestprod.load(scanner);
			System.out.println(o.solve());
		}
	}

}
