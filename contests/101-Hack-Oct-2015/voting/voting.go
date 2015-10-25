package main

import (
	"os"
	"io"
	"fmt"
	"log"
)

func solve(A []string) string {
	m := make(map[string]int)
	max := 0
	imax := 0
	for i, v := range A {
		m[v]++
		if m[v] > max {
			max = m[v]
			imax = i
		}
	}
	return A[imax]
}

func load(stdin io.Reader) {
	var n int
	fmt.Fscan(stdin, &n)
	A := make([]string, n)
	for i := 0; i < len(A); i++ {
		fmt.Fscanln(stdin, &A[i])
	}
	fmt.Println(solve(A))
}

func main() {
	if len(os.Args) == 2 && os.Args[1] == "COHADAR" {
		f, err := os.Open("voting.in")
		if err != nil {
			log.Fatal(err)
		}
		defer f.Close()
		load(f)
	} else {
		load(os.Stdin)
	}
}