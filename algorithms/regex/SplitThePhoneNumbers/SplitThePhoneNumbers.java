import java.util.*;
import java.io.*;
// import java.util.regex.*;

public class SplitThePhoneNumbers {

	static final String CC = "(\\d{1,3})";
	static final String LAC = CC;
	static final String NUM = "(\\d{4,10})";
	static final String SEP = "[- ]";

	// static final Pattern PATTERN = Pattern.compile(CC + SEP + LAC + SEP + NUM);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			String s = scanner.nextLine();
			// Matcher m = PATTERN.matcher(s);
			// if (!m.matches()) { throw new IllegalArgumentException("Bad Input: " + s);}
			// String cc = m.group(1);
			// String lac = m.group(2);
			// String num = m.group(3);
			// System.out.printf("CountryCode=%s,LocalAreaCode=%s,Number=%s\n", cc, lac, num);
			System.out.println(s.replaceAll(CC + SEP + LAC + SEP + NUM, "CountryCode=$1,LocalAreaCode=$2,Number=$3"));
		}
	}

}

