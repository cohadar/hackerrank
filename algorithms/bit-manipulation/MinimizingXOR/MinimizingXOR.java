import java.util.*;
import java.io.*;

public class MinimizingXOR {

	static int maxXor(int l, int r) {
		int max = l ^ r;
		for (int a = l; a <= r - 1; a++) {
			for (int b = a; b <= r; b++) {
				int t = a ^ b;
				if (t > max) {
					max = t;
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int l = scanner.nextInt();
		int r = scanner.nextInt();
		System.out.println(maxXor(l, r));
	}

}

