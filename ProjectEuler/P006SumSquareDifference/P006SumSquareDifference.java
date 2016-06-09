import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class P006SumSquareDifference {

	final int n;
	
	public P006SumSquareDifference(int n) {
		this.n = n;
	}

	public long solve() {
		long sum = 0;
		long ss = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
			ss += i * i;
		}
		return Math.abs(ss - sum * sum);
	}

	public static P006SumSquareDifference load(Scanner scanner) {
		int n = scanner.nextInt();
		return new P006SumSquareDifference(n);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			P006SumSquareDifference o = P006SumSquareDifference.load(scanner);
			System.out.println(o.solve());
		}
	}

}
