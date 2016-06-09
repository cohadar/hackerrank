import math;

def sieve(n):
	P = [0] * n
	P[2] = 1
	for i in xrange(3, n, 2):
		P[i] = 1
	for i in xrange(3, int(math.sqrt(n)) + 1, 2):
		if P[i] == 1:
			for j in xrange(i * i, n, i):
				P[j] = 0
	return P


def main():
	P = sieve(200000)
	R = [i for i, v in enumerate(P) if v == 1]
	tt = int(raw_input())
	for _ in xrange(tt):
		nn = int(raw_input())
		print R[nn - 1]

if __name__ == '__main__':
	main()