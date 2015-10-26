#!/usr/bin/python
import sys
import random

def print_all(n, low, high):
	for x in xrange(n):
		print random.randrange(low, high)

if __name__ == '__main__':
	if len(sys.argv) == 4:
		print_all(int(sys.argv[1]), int(sys.argv[2]), int(sys.argv[3]))
	else:
		print "usage: rand.py count low high"