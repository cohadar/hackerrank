import java.util.*;
import java.io.*;

public class MakeItAnagram {

	static int deletions(char[] a, char[] b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int ia = 0;
		int ib = 0;
		int dels = 0;
		while (true) {
			if (a[ia] == b[ib]) {
				ia++;
				ib++;
				if (ia == a.length) { break; }
				if (ib == b.length) { break; }
			} else if (a[ia] < b[ib]){
				ia++;
				dels++;
				if (ia == a.length) { break; }
			} else {
				ib++;
				dels++;
				if (ib == b.length) { break; }
			}
		}
		return dels + (b.length - ib) + (a.length - ia);
	}

	static void load(Scanner scanner) {
		String a = scanner.nextLine();
		String b = scanner.nextLine();
		System.out.println(deletions(a.toCharArray(), b.toCharArray()));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("MakeItAnagram.in"));
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

