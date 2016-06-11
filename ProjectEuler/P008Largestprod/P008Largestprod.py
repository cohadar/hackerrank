
def solve(nn, kk, C):
	amax = 0
	for l in xrange(0, nn - kk + 1):
		p = 1
		for i in xrange(0, kk):
			p *= int(C[l + i])
		amax = max(amax, p)
	return amax

def main():
	for _ in xrange(input()):
		nn, kk = map(int, raw_input().split())
		C = raw_input()
		print solve(nn, kk, C)

if __name__ == '__main__':
	main()