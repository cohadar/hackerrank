import java.util.*;
import java.io.*;

public class FindHackerRank {

	static int solve(String s) {
		boolean starts = s.startsWith("hackerrank");
		boolean ends = s.endsWith("hackerrank");
		return (starts) ? ((ends) ? 0 : 1) : ((ends) ? 2 : -1);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String s = scanner.nextLine();
			System.out.println(solve(s));
		}
	}

}

