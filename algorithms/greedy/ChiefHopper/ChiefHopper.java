import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class ChiefHopper {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] H = scanArray(scanner, n);
		long e = 0;
		for (int i = H.length - 1; i >= 0; i--) {
			e = (H[i] + e) / 2 + (H[i] + e) % 2;
		}
		System.out.println(e);
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

