#!/usr/bin/python
import os
import sys
import time

template_py = """#!/usr/bin/python
import sys

def solve(a, b):
	return a + b

def load(stdin):
	T = int(stdin.readline())
	for i in xrange(T):
		a, b = map(int, stdin.readline().split(" "))
		print solve(a, b)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load(open("template-name.in", "r"))
	else:
		load(sys.stdin)
"""

template_c = """#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

int solve(int a, int b)
{
	return a + b;
}

void load(FILE * in)
{
	int T;
	int a, b;
	fscanf(in, "%d\\n", &T);
	for (int i = 0; i < T; i++) {
		fscanf(in, "%d %d\\n", &a, &b);
		printf("%d\\n", solve(a, b));
	}
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("template-name.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}
"""

template_java = """import java.util.*;
import java.io.*;

public class TemplateName {

	int solve(int a, int b) {
		return a + b;
	}

	static void load(Scanner scanner) {
		int T = scanner.nextInt();
		for (int i = 0; i < T; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			System.out.println(new TemplateName().solve(a, b));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("template-name.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
"""

template_in = """3
1 2
3 4
5 6
"""

template_timing = """## template-name
FIRST: (.c)
	00:00 - started
"""

def c_to_upper(name):
	ret = name[0].upper()
	next = False
	for c in name[1:]:
		if c in  '-_':
			next = True
		else:
			if next:
				ret += c.upper()
			else:
				ret += c
			next = False
	return ret

def make_file(name, ext, template):
	uname = c_to_upper(name)
	basename = uname if ext == ".java" else name
	filename = os.path.join(name, basename + ext)
	if not os.path.exists(filename):
		print "making:", filename
		with open(filename, "w") as f:
			f.write(template.replace("template-name", name).replace("TemplateName", uname))
	else:
		print "exists:", filename

def make_all(name):
	if not os.path.exists(name):
		print "making:", name
		os.mkdir(name)
	else:
		print "exists:", name
	make_file(name, ".py", template_py)
	make_file(name, ".c", template_c)
	make_file(name, ".java", template_java)
	make_file(name, ".in", template_in)
	make_file(name, ".timing", template_timing.replace("00:00", time.strftime("%H:%M")))

if __name__ == '__main__':
	if len(sys.argv) == 2:
		make_all(sys.argv[1])
	else:
		print "usage: hrtemplate.py template-name"
