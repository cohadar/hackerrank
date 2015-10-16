#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

int hour24(int hour12, char c)
{
	if (hour12 == 12) {
		hour12 = 0;
	}
	if (c == 'A') {
		return hour12;
	} else if (c == 'P') {
		return hour12 + 12;
	}
	assert(0); // bad format
}

void load(FILE * in)
{
	int hh, mm, ss;
	char c;
	fscanf(in, "%02d:%02d:%02d%cM\n", &hh, &mm, &ss, &c);
	printf("%02d:%02d:%02d\n", hour24(hh, c), mm, ss);
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("time-conversion.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

