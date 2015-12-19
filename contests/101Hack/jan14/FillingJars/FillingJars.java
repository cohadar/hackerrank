import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class FillingJars {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		long count = 0;
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int k = scanner.nextInt();
			count += (long)k * (b - a + 1);
		}
		System.out.println(count / n);
	}

}

