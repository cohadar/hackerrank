import java.util.*;
import java.io.*;

public class IntegralPoints {

	static class Point {
		final double x;
		final double y;

		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		double distance(Point that) {
			return Math.hypot(this.x - that.x, this.y - that.y);
		}

		long integralPointsOnALine(Point that) {
			long dx = Math.round(Math.abs(that.x - this.x));
			long dy = Math.round(Math.abs(that.y - this.y));
			return gcd(dx, dy) + 1;
		}
	}

	static long gcd(long a, long b) {
		if (a < b) {
			return gcd(b, a);
		}
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	long solve(Point a, Point b, Point c) {
		double sa = b.distance(c);
		double sb = c.distance(a);
		double sc = a.distance(b);
		double s = (sa + sb + sc) / 2;
		double area = Math.sqrt(s * (s  - sa) * (s  - sb) * (s  - sc));
		long area_x2 = Math.round(area * 2);
		// System.out.printf("area=%g\n", area);
		long boundaryPoints = -3;
		boundaryPoints += a.integralPointsOnALine(b);
		boundaryPoints += b.integralPointsOnALine(c);
		boundaryPoints += c.integralPointsOnALine(a);
		// System.out.printf("boundaryPoints=%d\n", boundaryPoints);
		// System.out.printf("ret=%g\n", area - boundaryPoints / 2.0 + 1.0);
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
