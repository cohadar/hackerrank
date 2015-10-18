#!/usr/bin/python
import sys

def solve(ar):

	return len(ar)

def load_single(stdin, cohadar, test_case):
	N = int(stdin.readline())
	if cohadar:
		ar = map(int, stdin.readline().split())
	else:
		ar = []
		for i in xrange(N):
			ar.append(int(stdin.readline()))
	assert(len(ar) == N)
	print solve(ar)

def load_multi(stdin, cohadar):
	T = int(stdin.readline())
	for i in xrange(T):
		load_single(stdin, cohadar, i + 1)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load_multi(open("candies.in", "r"), True)
	else:
		load_single(sys.stdin, False, 0)