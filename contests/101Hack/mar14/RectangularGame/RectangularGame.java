import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class RectangularGame {

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int minA = INF;
		int minB = INF;
		for (int i = 0; i < n; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			minA = Math.min(minA, a);
			minB = Math.min(minB, b);
		}
		System.out.println((long)minA * minB);
	}

}

