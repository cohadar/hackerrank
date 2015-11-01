import java.util.*;
import java.io.*;

public class HackerRankTweets {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		int c = 0;
		while (t-->0) {
			if (scanner.nextLine().toLowerCase().contains("hackerrank")) {
				c++;
			}
		}
		System.out.println(c);
	}

}

