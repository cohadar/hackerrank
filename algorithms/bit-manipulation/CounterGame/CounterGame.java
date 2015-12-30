import java.util.*;
import java.io.*;
import java.math.BigInteger;

/* Mighty Cohadar */
public class CounterGame {

	static boolean solve(BigInteger b) {
		boolean ret = false;
		BigInteger TWO = new BigInteger("2");
		// is it 1?
		while (!BigInteger.ONE.equals(b)) {
			// is it power of 2?
			if (b.bitCount() == 1) {
				// divide by 2
				b = b.divide(TWO);
			} else {
				// clear msb
				b = b.clearBit(b.bitLength() - 1);
			}
			ret = !ret;
		}
		return ret;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String n = scanner.nextLine();
			BigInteger b = new BigInteger(n);
			System.out.println((solve(b)) ? "Louise" : "Richard");
		}
	}

}

