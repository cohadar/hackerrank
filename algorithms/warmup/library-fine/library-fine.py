#!/usr/bin/python
import sys
import datetime

def solve(actual, expected):
	if actual < expected:
		return 0
	if actual.year == expected.year:
		if actual.month == expected.month:
			return (actual.day - expected.day) * 15
		else:
			return (actual.month - expected.month) * 500
	return 10000

def load_date(stdin):
	D, M, Y = map(int, stdin.readline().split(" "))
	assert(1 <= D and D <= 31);
	assert(1 <= M and M <= 12);
	assert(1 <= Y and Y <= 3000);
	return datetime.date(Y, M, D);


def load(stdin):
	actual = load_date(stdin)
	expected = load_date(stdin)
	print solve(actual, expected)

if __name__ == '__main__':
	if len(sys.argv) == 2 and sys.argv[1] == "COHADAR":
		load(open("library-fine.in", "r"))
	else:
		load(sys.stdin)
