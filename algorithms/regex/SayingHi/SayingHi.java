import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SayingHi {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String s = scanner.nextLine();
			if (s.matches("[hH][iI] [^ dD].*")) {
				System.out.println(s);
			}
		}
	}

}

