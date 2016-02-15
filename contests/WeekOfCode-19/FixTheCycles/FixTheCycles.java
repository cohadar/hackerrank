import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class FixTheCycles {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		int d = scanner.nextInt();
		int e = scanner.nextInt();
		int f = scanner.nextInt();
		int sum = a + b + c + d + e + f;
		assert -120 <= sum && sum <= 120 : "out of range, sum: " + sum;
		int c1 = b + f + a;
		int c2 = e + d + a;
		int c3 = b + c + d + a;
		int minc = Math.min(c1, Math.min(c2, c3));
		int p = Math.max(0, -minc);
		System.out.println(p);
	}

}

