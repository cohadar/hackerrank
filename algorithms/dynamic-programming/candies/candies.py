#!/usr/bin/python
import sys

sys.setrecursionlimit(10111)

def split_on_even(ar):
	if len(ar) == 0:
		return []
	arz = []
	cumul = []
	cumul.append(ar[0])
	prev = ar[0]
	for v in ar[1:]:
		if v == prev:
			arz.append(cumul)
			cumul = []
		cumul.append(v)
		prev = v
	if cumul:
		arz.append(cumul)
	return arz

def test_split_on_even():
	assert(split_on_even([]) == [])
	assert(split_on_even([1]) == [[1]])
	assert(split_on_even([1, 1]) == [[1], [1]])
	assert(split_on_even([1, 1, 1]) == [[1], [1], [1]])
	assert(split_on_even([1, 2, 3, 3]) == [[1, 2, 3], [3]])
	assert(split_on_even([1, 1, 2, 3]) == [[1], [1, 2, 3]])
	assert(split_on_even([1, 2, 3, 3, 2, 1]) == [[1, 2, 3], [3, 2, 1]])

def split_on_peak(segment):
	desc = True
	prev = 10000000
	ret = []
	cumul = []
	for i, v in enumerate(segment):
		if desc:
			if prev > v:
				cumul.append(v)
			else:
				desc = False
				cumul.append(v)
		else:
			if prev < v:
				cumul.append(v)
			else:
				ret.append(cumul)
				cumul = []
				cumul.append(v)
		prev = v
	if len(cumul) > 0:
		ret.append(cumul)
	return ret

def test_split_on_peak():
	assert(split_on_peak([]) == [])
	assert(split_on_peak([1]) == [[1]])
	assert(split_on_peak([1, 2, 3]) == [[1, 2, 3]])
	assert(split_on_peak([3, 2, 1]) == [[3, 2, 1]])
	assert(split_on_peak([3, 2, 1, 2, 3]) == [[3, 2, 1, 2, 3]])
	print split_on_peak([1, 2, 1, 2, 1])
	assert(split_on_peak([1, 2, 1, 2, 1]) == [[1, 2], [1, 2], [1]])
	assert(split_on_peak([2, 1, 2, 1, 2, 3]) == [[2, 1, 2], [1, 2, 3]])

def convex_tuple(convex):
	left = 0
	right = 0
	suma = 0
	m = 0
	delta = -1
	prev = -1000000
	for v in convex:
		if prev < v:
			delta += 1
		else:
			delta -= 1
		suma += delta
		if delta < m:
			m = delta
		prev = v
	return (-m, suma + len(convex) * (-m), delta - m)

def test_convex_tuple():
	# left, sum ,right
	assert(convex_tuple([11, 22, 33]) == (0, 3, 2))
	assert(convex_tuple([33, 22, 11]) == (2, 3, 0))
	assert(convex_tuple([33, 22, 11, 22, 33, 44]) == (2, 9, 3))
	assert(convex_tuple([44, 33, 22, 11, 22, 33]) == (3, 9, 2))

def solve_segment(segment):
	convexz = split_on_peak(segment)
	ctu = map(convex_tuple, convexz)
	return len(segment)

def solve(ar):
	arz = split_on_even(ar)
	return sum([solve_segment(s) for s in arz])

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
	test_split_on_even()
	test_split_on_peak()
	test_convex_tuple()
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		#load_single(open("candies.in", "r"), False, 0)
		load_multi(open("candies.in", "r"), True)
	else:
		load_single(sys.stdin, False, 0)