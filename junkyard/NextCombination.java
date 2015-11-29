import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  * @link 
  */
public class NextCombination {

	static boolean nextCombination(int[] S, int n) {
		int r = S.length;
		for (int i = r - 1; i >= 0; i--) {
			if (S[i] != n - r + i) {
				S[i]++;
				for (int j = i + 1; j < r; j++) {
					S[j] = S[j - 1] + 1;
				}
				return true;
			}
		}		
		return false;
	}

	public static void main(String[] args) {
		int[] S = new int[] {0, 1, 2};
		debug(S);
		while (nextCombination(S, 5)) {
			debug(S);
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
