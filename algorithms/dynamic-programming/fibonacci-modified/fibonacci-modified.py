#!/usr/bin/python
import sys

# why is this even classified as dynamic programming?
def solve(a, b, n):
	assert(n > 0)
	if n == 1:
		return a
	if n == 2:
		return b
	for _ in xrange(3, n + 1):
		b, a = b * b + a, b
	return b

def load(stdin):
	A, B, N = map(int, stdin.readline().split(" "))
	assert(0 <= A and A <= 2)
	assert(0 <= B and B <= 2)
	assert(3 <= N and N <= 20)
	print solve(A, B, N)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load(open("fibonacci-modified.in", "r"))
	else:
		load(sys.stdin)