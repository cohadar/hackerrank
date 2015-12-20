import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  * @link 
  */
public class JimAndTheOrders {

	static class Pair implements Comparable<Pair> {
		final int a;
		final int b;
		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public int compareTo(Pair that) {
			if (this.a == that.a) {
				return Integer.compare(this.b, that.b);
			} else {
				return Integer.compare(this.a, that.a);
			}
		}
		public String toString() {
			return String.format("(a=%d, b=%d)", a, b);
		}	
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Pair[] A = new Pair[n];
		for (int i = 0; i < n; i++) {
			int t = scanner.nextInt();
			int d = scanner.nextInt();
			A[i] = new Pair(t + d, i + 1);
		}
		Arrays.sort(A);
		System.out.println(join(A, ' '));
	}

	static String join(Pair[] A, char delimiter) {
		StringBuilder sb = new StringBuilder();
		for (Pair o : A) {
			sb.append(o.b);
			sb.append(delimiter);
		}
		return sb.toString();
	}

}
