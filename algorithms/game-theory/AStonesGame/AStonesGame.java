import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class AStonesGame {

	static int solve(int n) {
		int h = 1;
		while ((h << 1) <= n) {
			h <<= 1;
		}
		return h - 1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 1e6 : "out of range, t: " + t;
		while (t-->0) {
			int n = scanner.nextInt();
			assert 1 <= n && n <= 1e9 : "out of range, n: " + n;
			System.out.println(solve(n));
		}
	}

}
