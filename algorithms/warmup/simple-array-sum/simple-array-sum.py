#!/usr/bin/python
import sys

def solve(a, b):
	return a + b

def load(stdin):
	N = int(stdin.readline())
	A = map(int, stdin.readline().split(" "))
	assert(N == len(A))
	print sum(A)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load(open("simple-array-sum.in", "r"))
	else:
		load(sys.stdin)