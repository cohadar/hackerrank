import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class FlippingBits {

	static long flip(long a) {
		return (~a) & 0xFFFFFFFFL;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			long a = scanner.nextLong();
			System.out.println(flip(a));
		}
	}

}

