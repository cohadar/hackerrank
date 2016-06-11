import java.util.*;
import java.io.*;
import java.math.*;

/* Mighty Cohadar */
public class P013LargeSum {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int nn = scanner.nextInt();
		scanner.nextLine();
		BigDecimal p = BigDecimal.ONE;
		while (nn-->0) {
			p = p.add(new BigDecimal(scanner.nextLine()));
		}
		System.out.println(p.toString().substring(0, 10));
	}

}

