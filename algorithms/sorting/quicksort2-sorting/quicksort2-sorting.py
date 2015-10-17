#!/usr/bin/python
import sys

# need to use list partitioning because challenge is idiotic
def partition(ar, l, r):
	p = ar[l]
	ll = []
	rr = []
	for v in ar[l+1:r+1]:
		if v < p:
			ll.append(v)
		else:
			rr.append(v)
	ll.append(p)
	ret = l + len(ll) - 1
	ll.extend(rr)
	for i, v in enumerate(ll):
		ar[l + i] = v
	return ret

def qsort(ar, l, r):
	if l < r:
		p = partition(ar, l, r)
		qsort(ar, l, p - 1)
		qsort(ar, p + 1, r)
		print " ".join(map(str, ar[l:r+1]))

def load(stdin):
	n = int(stdin.readline())
	ar = map(int, stdin.readline().split(" "))
	assert(n == len(ar))
	qsort(ar, 0, n - 1)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load(open("quicksort2-sorting.in", "r"))
	else:
		load(sys.stdin)

