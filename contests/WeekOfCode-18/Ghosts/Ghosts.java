import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Ghosts {

	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	static long solve(int A, int B, int C, int D) {
		long count = 0;
		for (int a = 1; a <= A; a++) {
		for (int b = 1; b <= B; b++) {
		for (int c = 1; c <= C; c++) {
		for (int d = 1; d <= D; d++) {
			if (Math.abs(a - b) % 3 == 0) {
				if ((b + c) % 5 == 0) {
					if ((a * c) % 4 == 0) {
						if (gcd(a, d) == 1) {
							count++;
						}
					}
				}
			}
		}
		}
		}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		int d = scanner.nextInt();
		assert 1 <= a && a <= 60 : "out of range, a: " + a;
		assert 1 <= b && b <= 60 : "out of range, b: " + b;
		assert 1 <= c && c <= 60 : "out of range, c: " + c;
		assert 1 <= d && d <= 60 : "out of range, d: " + d;
		System.out.println(solve(a, b, c, d));
	}

}

