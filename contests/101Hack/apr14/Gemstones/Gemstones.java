import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Gemstones {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine());
		boolean[] C = new boolean[256];
		Arrays.fill(C, true);
		for (int i = 0; i < n; i++) {
			String s = scanner.nextLine();
			for (int j = 'a'; j <= 'z'; j++) {
				if (s.indexOf(j) < 0) {
					C[j] = false;
				}		
			}	
		}
		int count = 0;
		for (int j = 'a'; j <= 'z'; j++) {
			if (C[j]) {
				count++;
			}		
		}			
		System.out.println(count);
	}

}

