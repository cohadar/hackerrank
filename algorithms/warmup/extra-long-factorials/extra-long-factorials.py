#!/usr/bin/python
import sys

def solve(n):
	p = 1
	for i in xrange(2, n + 1):
		p *= i
	return p

def load(stdin):
	N = int(stdin.readline())
	print solve(N)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load(open("extra-long-factorials.in", "r"))
	else:
		load(sys.stdin)