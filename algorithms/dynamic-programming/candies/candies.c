#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdbool.h>
#include <stdint.h>

#define MIN(a,b) ((a)<(b)?(a):(b))

#define MAX_CHILDREN 102345
static long long R[MAX_CHILDREN]; // ratings

enum Stage {CONTINUE_ASC = 3, CONTINUE_DESC = 0, BOTTOM = 1, PEAK_BREAK = 2};

typedef struct {
	long long left;
	long long right;
	long long sum;
	long long len;
	bool eq;
} Segment;

typedef struct {
	long long partial;
	bool asc;
	long long d;
	long long m;
	long long len;
} State;

void State_print(State *o)
{
	// printf("l=%lld, asc=%d, m=%lld, d=%lld, part=%lld\n", o->len, o->asc, o->m, o->d, o->partial);
}

void State_init(State *o)
{
	o->partial = 0;
	o->asc = false;
	o->d = 0;
	o->m = 0;
	o->len = 1;
}

void State_continue_asc(State *o)
{
	o->d += 1;
	o->partial += o->d;
	o->len++;
}

void State_continue_desc(State *o)
{
	o->d -= 1;
	o->partial += o->d;
	o->len++;
	if (o->d < o->m) { o->m = o->d; }
}

void State_bottom(State *o)
{
	o->d += 1;
	o->partial += o->d;
	o->len++;
	o->asc = true;
}

Segment State_peak(State *o)
{
	Segment ret;
	State_init(o);
	return ret;
}

Segment State_even(State *o)
{
	Segment ret;
	State_init(o);
	return ret;
}

long long Result_add(Segment prev, Segment curr)
{
	return 1;
}

long long solve(int N)
{
	long long total = 0;
	Segment prev;
	Segment curr;
	State state;
	State_init(&state);
	State_print(&state);
	bool segment_end = false;
	for (int i = 1; i < N; i++) {
		segment_end = false;
		if (R[i - 1] == R[i]) {
			curr = State_even(&state);
			total += Result_add(prev, curr);
			prev = curr;
			State_print(&state);
			segment_end = true;
			continue;
		}
		enum Stage stage = (state.asc << 1) + (R[i - 1] < R[i]);
		switch (stage) {
			case CONTINUE_ASC:
				State_continue_asc(&state);
				State_print(&state);
				break;
			case CONTINUE_DESC:
				State_continue_desc(&state);
				State_print(&state);
				break;
			case BOTTOM:
				State_bottom(&state);
				State_print(&state);
				break;
			case PEAK_BREAK:
				curr = State_peak(&state);
				total += Result_add(prev, curr);
				prev = curr;
				State_print(&state);
				segment_end = true;
				break;
			default:
				assert(0);
		}
	}
	if (!segment_end) {
		curr = State_even(&state);
		total += Result_add(prev, curr);
		prev = curr;
		State_print(&state);
	}
	State_print(&state);
	return total + N;
}

void load_single(FILE *in, bool cohadar, int test_case)
{
	int N;
	fscanf(in, "%d\n", &N);
	for (int i = 0; i < N; i++) {
		fscanf(in, "%lld", &R[i]);
	}
	printf("%lld\n", solve(N));
}

void load_multi(FILE *in, bool cohadar) {
	int T;
	fscanf(in, "%d\n", &T);
	for (int t = 1; t <= T; t++) {
		load_single(in, cohadar, t);
	}
}

int main(int argc, char const *argv[])
{
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		FILE *in = fopen("candies.in", "r");
		assert(in);
		load_multi(in, true);
	} else {
		load_single(stdin, false, 0);
	}
	return 0;
}

