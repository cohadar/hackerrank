import java.util.*;
import java.io.*;

public class FlippingBits {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			long l = scanner.nextLong();
			System.out.println(l ^ (1L << 32) - 1);
		}
	}

}

