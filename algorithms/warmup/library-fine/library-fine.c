#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <time.h>

int solve(time_t actual, time_t expected)
{
	struct tm a = *gmtime(&actual);
	struct tm e = *gmtime(&expected);
	if (difftime(actual, expected) <= 0.0) {
		return 0;
	}
	if (a.tm_year == e.tm_year) {
		if (a.tm_mon == e.tm_mon) {
			return (a.tm_mday - e.tm_mday) * 15;
		} else {
			return (a.tm_mon - e.tm_mon) * 500;
		}
	}
	return 10000;
}

time_t load_date(FILE *in)
{
	int D, M, Y;
	fscanf(in, "%d %d %d\n", &D, &M, &Y);
	assert(1 <= D && D <= 31);
	assert(1 <= M && M <= 12);
	assert(1 <= Y && Y <= 3000);
	struct tm *temp = calloc(1, sizeof(*temp));
	temp->tm_mday = D;
	temp->tm_mon = M - 1;
	temp->tm_year = Y - 1900; // lol at C
	time_t ret = mktime(temp);
	free(temp);
	return ret;
}

void load(FILE *in)
{
	time_t actual = load_date(in);
	time_t expected = load_date(in);
	printf("%d\n", solve(actual, expected));
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("library-fine.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

