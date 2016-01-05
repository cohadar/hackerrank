import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TwosComplement {

	static final int SEGMENT_WIDTH = 16;
	static final int SEGMENT_MASK = 0xFFFF;
	static final int SEGMENT_SIZE = 0xFFFF + 1;
	static final int BITS_PER_SEGMENT = 524288;

	static long positiveBitCount(int a) {
		assert a >= 0;
		if (a == 0) { 
			return 0; 
		};
		if (a % 2 == 0) {
			return Integer.bitCount(a) + positiveBitCount(a - 1);
		} else {
			return 2 * positiveBitCount(a / 2) + (a + 1L) / 2;
		}
	}

	static long positiveBitCount(int a, int b) {
		assert 0 <= a && a <= b;
		if (a == 0) {
			return positiveBitCount(b);	
		} else {
			return positiveBitCount(b) - positiveBitCount(a - 1);
		}
	}

	static long negativeBitCount(int a) {
		assert a < 0;
		return positiveBitCount(a & Integer.MAX_VALUE, Integer.MAX_VALUE) - (long)a;	
	}	

	static long negativeBitCount(int a, int b) {
		assert a <= b && b < 0;
		if (b == -1) {
			return negativeBitCount(a);
		} else {
			return negativeBitCount(a) - negativeBitCount(b + 1);
		}
	}

	static long bitCount(int a, int b) {
		assert a <= b;
		if (a >= 0 && b >= 0) {
			return positiveBitCount(a, b);
		}
		if (a < 0 && b < 0) {
			return negativeBitCount(a, b);
		}
		return negativeBitCount(a) + positiveBitCount(b);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = scanner.nextInt();
		while (t-->0) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			sb.append(bitCount(a, b));
			sb.append('\n');
		}
		System.out.println(sb);
	}

}

