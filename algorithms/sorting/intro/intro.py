#!/usr/bin/python
import sys

def solve(ar, v):
	l = 0
	r = len(ar) - 1
	while l <= r:
		m = (l + r) / 2
		if ar[m] < v:
			l = m + 1
		elif v < ar[m]:
			r = m - 1
		else:
			return m
	return -(l + 1)

def load(stdin):
	V = int(stdin.readline())
	n = int(stdin.readline())
	ar = map(int, stdin.readline().split(" "))
	print solve(ar, V)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load(open("intro.in", "r"))
	else:
		load(sys.stdin)