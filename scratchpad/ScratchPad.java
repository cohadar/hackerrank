import java.util.*;
import java.io.*;

public class ScratchPad {

    static int compare(int a, int b) {
        return a - b;
    }

    static int sign(int x) {
        return (x < 0) ? -1 : ((x > 0) ? 1 : 0);
    }

    public static void main(String[] args) {
        int a = 2000000000;
        int b = -a;
        if (sign(compare(a, b)) != sign(Integer.compare(a, b))) {
            System.out.printf("%d %d %d %d\n", a, b, compare(a, b), Integer.compare(a, b));
        }
    }

}
