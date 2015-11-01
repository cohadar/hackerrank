import java.util.*;
import java.io.*;

public class JimAndTheOthers {

	static class Fan implements Comparable<Fan>{
		final int index;
		final int done;
		Fan(int index, int done) {
			this.index = index;
			this.done = done;
		}
		public int compareTo(Fan that) {
			if (this.done == that.done) {
				return Integer.compare(this.index, that.index);
			}
			return Integer.compare(this.done, that.done);
		}
		public String toString() {
			return String.valueOf(index);
		}	
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine());
		Fan[] fans = new Fan[n];
		for (int i = 1; i <= n; i++) {
			int t = scanner.nextInt();
			int d = scanner.nextInt();
			fans[i - 1] = new Fan(i, t + d);
		}
		Arrays.sort(fans);
		System.out.println(join(fans, ' '));
	}

	static String join(Object[] A, char delimiter) {
		StringBuilder sb = new StringBuilder();
		for (Object o : A) {
			sb.append(o.toString());
			sb.append(delimiter);
		}
		return sb.toString();
	}

}

