def main():
	sum = 0
	for _ in xrange(input()):
		sum += input()
	print str(sum)[0:10]

if __name__ == '__main__':
	main()