#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <limits.h>

static int D[100][100];
static int dist[100];
static int prev[100];

typedef struct {
	int len;
	int data[100];
} QS;

static QS Q;

void init_D()
{
	for (int i = 0; i < 100; i++) {
	for (int j = 0; j < 100; j++) {
		D[i][j] = -1;
		if (i <= j && (j - i) <= 6) {
			D[i][j] = j - i;
		}
	}
	}
}

int popmindist_Q()
{
	int m = INT_MAX;
	int im = -1;
	for (int iq = 0; iq < Q.len; iq++) {
		int a = Q.data[iq];
		if (dist[a] >= 0) {
			if (dist[a] < m) {
				m = dist[a];
				im = iq;
			}
		}
	}
	assert(0 <= im && im < 100);
	int ret = Q.data[im];
	Q.data[im] = Q.data[--Q.len];
	return ret;
}

int solve()
{
	Q.len = 0;
	for (int a = 0; a < 100; a++) {
		dist[a] = -1;
		prev[a] = -1;
		Q.data[Q.len++] = a; // append
	}
	dist[0] = 0; // source node
	while (Q.len) {
		int a = popmindist_Q();
		for (int b = 0; b < 100; b++) {
			if ((a != b) && (D[a][b] >= 0)) {
				int db = dist[a] + D[a][b];
				if (db < dist[b] || dist[b] < 0) {
					dist[b] = db;
					prev[b] = a;
				}
			}
		}
	}
	int hops = 0;
	int curr = 99;
	while (curr > 0) {
		if (prev[curr] < 0) {
			return -1;
		}
		if (D[prev[curr]][curr] > 0) {
			hops++;
		}
		curr = prev[curr];
	}
	return hops;
}

void load(FILE * in)
{
	int T, NL, NS;
	fscanf(in, "%d\n", &T);
	for (int i = 0; i < T; i++) {
		init_D();
		fscanf(in, "%d\n", &NL);
		for (int l = 0; l < NL; l++) {
			int a, b;
			fscanf(in, "%d %d\n", &a, &b);
			D[a - 1][b - 1] = 0;
		}
		fscanf(in, "%d\n", &NS);
		for (int s = 0; s < NS; s++) {
			int a, b;
			fscanf(in, "%d %d\n", &a, &b);
			D[a - 1][b - 1] = 0;
		}
		printf("%d\n", solve());
	}
}

int main(int argc, char const *argv[])
{
	FILE *in;
	if (argc == 2 && strcmp(argv[1], "COHADAR") == 0) {
		in = fopen("snakes-and-ladders.in", "r");
		assert(in);
	} else {
		in = stdin;
	}
	load(in);
	return 0;
}

