import java.util.*;
import java.io.*;

public class IntegralPoints {

	static class Point {
		final long x;
		final long y;

		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		long integralPointsOnALine(Point that) {
			long dx = Math.abs(that.x - this.x);
			long dy = Math.abs(that.y - this.y);
			if (dx > dy) {
				return gcd(dx, dy) + 1;
			} else {
				return gcd(dy, dx) + 1;
			}
		}
	}

	static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	long solve(Point a, Point b, Point c) {
		// Because 10^9 * 10^9 cannot fit in a double without loss of precision,
		// we must not use Heron's formula for triangle surface.
		// In fact, no doubles allowed.
		long area_x2 = Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y));
		// System.out.printf("area_x2=%d\n", area);
		long boundaryPoints = -3;
		boundaryPoints += a.integralPointsOnALine(b);
		boundaryPoints += b.integralPointsOnALine(c);
		boundaryPoints += c.integralPointsOnALine(a);
		// System.out.printf("boundaryPoints=%d\n", boundaryPoints);
		return (area_x2 - boundaryPoints + 2) / 2;
	}

	static Point loadPoint(Scanner scanner) {
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		return new Point(x, y);
	}

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			Point a = loadPoint(scanner);
			Point b = loadPoint(scanner);
			Point c = loadPoint(scanner);
			System.out.println(new IntegralPoints().solve(a, b, c));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("integral-points.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
