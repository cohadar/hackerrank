#!/usr/bin/python
import sys

def load(stdin):
	N = int(stdin.readline())
	for i in xrange(N):
		w = [' '] * (N - 1 - i)
		b = ['#'] * (i + 1)
		print "".join(w + b)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load(open("staircase.in", "r"))
	else:
		load(sys.stdin)