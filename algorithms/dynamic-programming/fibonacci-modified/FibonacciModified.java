import java.util.*;
import java.io.*;
import java.math.*;

public class FibonacciModified {

	BigInteger solve(BigInteger a, BigInteger b, int n) {
		assert(n > 0);
		if (n == 1) {
			return a;
		}
		if (n == 2) {
			return b;
		}
		for (int i = 3; i <= n; i++) {
			BigInteger tb = b.multiply(b).add(a);
			BigInteger ta = b;
			b = tb;
			a = ta;
		}
		return b;
	}

	void load(Scanner scanner) {
		int A = scanner.nextInt();
		int B = scanner.nextInt();
		int N = scanner.nextInt();
		assert(0 <= A && A <= 2);
		assert(0 <= B && B <= 2);
		assert(3 <= N && N <= 20);
		BigInteger bigA = new BigInteger(String.valueOf(A));
		BigInteger bigB = new BigInteger(String.valueOf(B));
		System.out.println(solve(bigA, bigB, N));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("fibonacci-modified.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		new FibonacciModified().load(scanner);
	}
}
