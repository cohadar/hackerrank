import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class P034DigitFact {

	public static boolean curious(int[] F, int c) {
		int sum = 0;
		char[] C = String.valueOf(c).toCharArray();
		for (int i = 0; i < C.length; i++) {
			sum += F[C[i] - '0'];
		}
		return sum % c == 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int nn = scanner.nextInt();
		assert 10 <= nn && nn <= 1e5 : "out of range, nn: " + nn;
		int[] F = new int[10];
		F[0] = 1;
		F[1] = 1;
		for (int i = 2; i <= 9; i++) {
			F[i] = F[i-1] * i;
		}
		int sum = 0;
		for (int i = 10; i < nn; i++) {
			if (curious(F, i)) {
				sum += i;
			}
		}
		System.out.println(sum);
	}

}

