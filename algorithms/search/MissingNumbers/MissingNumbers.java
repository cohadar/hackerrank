import java.util.*;
import java.io.*;

public class MissingNumbers {

	static List<Integer> findMissing(int[] A, int[] B) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < B.length; i++) {
			Integer v = map.get(B[i]);
			map.put(B[i], (v == null) ? 1 : v + 1);
		}
		for (int i = 0; i < A.length; i++) {
			Integer v = map.get(A[i]);
			map.put(A[i], v - 1);
		}
		List<Integer> list = new ArrayList<>();
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			assert e.getValue() >= 0;
			if (e.getValue() > 0) {
				list.add(e.getKey());
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] A = scanIntArray(scanner);
		int[] B = scanIntArray(scanner);
		debug(A);
		debug(B);
		List<Integer> M = findMissing(A, B);
		Collections.sort(M);
		System.out.println(join(M, ' '));
	}

	static int[] scanIntArray(Scanner scanner) {
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = scanner.nextInt();
		}
		return A;		
	}

	static String join(Collection<?> A, char delimiter) {
		StringBuilder sb = new StringBuilder();
		for (Object o : A) {
			sb.append(o.toString());
			sb.append(delimiter);
		}
		return sb.toString();
	}

	static void debug(Object...os) {
		// System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

