import java.util.*;
import java.io.*;

public class ValidPANFormat {


	static boolean isValid(String PAN) {
		return PAN.matches("[A-Z]{5}\\d{4}[A-Z]");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String PAN = scanner.nextLine();
			System.out.println((isValid(PAN)) ? "YES" : "NO");
		}
	}

}

