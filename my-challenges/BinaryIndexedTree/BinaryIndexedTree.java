import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class BinaryIndexedTree {

	static int left(int i) {
		return i & (i - 1);
	}

	static int right(int i) {
		return i + (i & -i);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

	}

}

