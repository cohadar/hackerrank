import java.util.*;
import java.io.*;
import java.util.regex.*;

public class UtopianIdentificationNumber {

	static final String PREFIX  = "[a-z]{0,3}";
	static final String MID     = "[0-9]{2,8}";
	static final String POSTFIX = "[A-Z]{3,}";

	static final Pattern PATTERN = Pattern.compile(PREFIX + MID + POSTFIX);

	static boolean isValid(String id) {
		return PATTERN.matcher(id).matches();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String id = scanner.nextLine();
			System.out.println((isValid(id)) ? "VALID" : "INVALID");
		}
	}

}

