import java.util.*;
import java.io.*;

public class EvenFibonacciNumbers {

	static Long[] precompute() {
		List<Long> list = new ArrayList<>();		
		list.add(2L);
		long a = 1;
		long b = 2; 
		while (true) {
			long tmp = b;
			b += a;
			a = tmp;
			if (b > 0) {
				if (b % 2 == 0) {
					list.add(b);
				}	
			} else {
				break;
			}
		}
		return list.toArray(new Long[0]);
	}

	static long solve(Long[] L, long n) {
		long sum = 0;
		for (int i = 0; i < L.length; i++) {
			if (L[i] <= n) {
				sum += L[i];
			} else {
				break;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		Long[] L = precompute();
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = scanner.nextInt();
		while (t-->0) {
			long n = scanner.nextLong();
			sb.append(solve(L, n));
			sb.append('\n');
		}
		System.out.println(sb);
	}

}

