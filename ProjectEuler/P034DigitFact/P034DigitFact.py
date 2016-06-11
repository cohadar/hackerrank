def curious(F, i):
	C = str(i)
	asum = 0;
	for c in C:
		asum += F[int(c)]
	return asum % i == 0

def main():
	nn = input()
	p = 1;
	F = [1]
	for x in xrange(1, 10):
		F.append(F[-1] * x)
	asum = 0;
	for i in xrange(10, nn):
		if curious(F, i):
			asum += i;
	print asum

if __name__ == '__main__':
	main()