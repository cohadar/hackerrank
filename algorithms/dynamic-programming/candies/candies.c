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
	long long total;
	long long partial;
	bool asc;
	long long d;
	long long m;
	long long len;
	long long seglen;
	long long prev_d;
} State;

void State_print(State *o)
{
	//printf("sl=%lld, l=%lld, asc=%d, m=%lld, d=%lld, prev_d=%lld, part=%lld, total=%lld\n", o->seglen, o->len, o->asc, o->m, o->d, o->prev_d, o->partial, o->total);
}

void State_init(State *o)
{
	o->total = 0;
	o->partial = 0;
	o->asc = false;
	o->d = 0;
	o->m = 0;
	o->len = 1;
	o->seglen = 1;
	o->prev_d = 1;
}

void State_continue_asc(State *o)
{
	o->d += 1;
	o->partial += o->d;
	o->len++;
	o->seglen++;
}

void State_continue_desc(State *o)
{
	o->d -= 1;
	o->partial += o->d;
	o->len++;
	o->seglen++;
	if (o->d < o->m) { o->m = o->d; }
}

void State_bottom(State *o)
{
	o->d += 1;
	o->partial += o->d;
	o->len++;
	o->seglen++;
	o->asc = true;
}

void State_peak(State *o)
{
	long long shift = o->prev_d - 1;
	if (o->m < 0 && -o->m < shift) {
		shift = -o->m;
	}
	o->d += shift;
	o->m += shift;
	o->total += o->partial + o->len * (shift);
	o->partial = 0;
	o->prev_d = o->d;
	o->d = 0;
	if (o->m < 0) {
		o->total += o->seglen * (-o->m);
		o->prev_d += -o->m;
	}
	o->m = 0;
	o->len = 1;
	o->seglen++;
	o->asc = false;
}

void State_even(State *o)
{
	State_peak(o);
	o->seglen = 1;
	o->prev_d = 1;
}

long long solve(int N)
{
	State state;
	State_init(&state);
	State_print(&state);
	for (int i = 1; i < N; i++) {
		if (R[i - 1] == R[i]) {
			State_even(&state);
			State_print(&state);
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
				State_peak(&state);
				State_print(&state);
				break;
			default:
				assert(0);
		}
	}
	State_even(&state);
	State_print(&state);
	return state.total + N;
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

