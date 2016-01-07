import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class AlienLanguages {

	static final int PRIME = (int)1e8 + 7;

	private final int n;
	private final int w;
	
	public AlienLanguages(int n, int w) {
		this.n = n;
		this.w = w;
	}

	int minNext(int i) {
		if (2 * i > n) {
			return 1;
		} else {
			return 2 * i;
		}
	}

	int firstCool(int n) {
		return (n & 1) / 2 + 1;
	}

	int minPrev(int j) {
		return Math.min((j & 1) / 2, firstCool(n));
	}
	
	long solve() {
		long[][] D = new long[n + 1][w + 1];
		for (int i = 0; i <= n; i++) {
			D[i][0] = 1;	
		}
		for (int iw = 1; iw <= w; iw++) {
			for (int in = 1; in <= n; in++) {
				D[in][iw] = 0;
				int p = minPrev(in);
				for (int ip = p; ip <= n; ip++) {
					D[in][iw] = D[ip][iw - 1] * Math.max(0, n - minNext(ip));
				}
			}
		}
		long sum = 0;
		for (int in = 1; in <= n; in++) {
			sum += D[in][w];
		}
		return sum;
	}

	static AlienLanguages load(Scanner scanner) {
		int n = scanner.nextInt();
		int w = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		assert 1 <= w && w <= 5e5 : "out of range, w: " + w;
		return new AlienLanguages(n, w);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 5 : "out of range, t: " + t;
		while (t-->0) {
			AlienLanguages o = AlienLanguages.load(scanner);
			System.out.println(o.solve());
		}
	}

}
