#!/usr/bin/python
import os
import sys
import time

template_py = """#!/usr/bin/python
/**
  * @author Mighty Cohadar 
  * @link 
  */
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
		load(open("TemplateName.in", "r"))
	else:
		load(sys.stdin)
"""

template_c = """/* Mighty Cohadar */
#include <stdio.h>
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
		in = fopen("TemplateName.in", "r");
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

/* Mighty Cohadar */
public class TemplateName {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

	}

}

"""

template_go = """
"""

template = {
	"c" : template_c,
	"go" : template_go,
	"py" : template_py,
	"java" : template_java,
}

template_timing = """## TemplateName
FIRST: (.lang)
	00:00 - started
"""

def make_file(name, ext, template):
	filename = os.path.join(name, name + ext)
	if not os.path.exists(filename):
		print "making:", filename
		with open(filename, "w") as f:
			f.write(template.replace("TemplateName", name))
	else:
		print "exists:", filename

def make_all(name):
	name, ext = name.split(".")
	if not os.path.exists(name):
		print "making:", name
		os.mkdir(name)
	else:
		print "exists:", name
	make_file(name, "." + ext, template[ext])
	# make_file(name, ".examp.in", "")
	# make_file(name, ".small.in", "")
	# make_file(name, ".large.in", "")
	make_file(name, ".timing", template_timing.replace("lang", ext).replace("00:00", time.strftime("%H:%M")))

if __name__ == '__main__':
	if len(sys.argv) == 2:
		make_all(sys.argv[1])
	else:
		print "usage: hrtemplate.py [ TemplateName.java | TemplateName.c | TemplateName.py | TemplateName.go ]"
