#!/usr/bin/python
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
		load(open("solve-me-second.in", "r"))
	else:
		load(sys.stdin)