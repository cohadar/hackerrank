package main

import (
	"os"
	"io"
	"fmt"
	"log"
)

func solve(n, c, m int) int {
	total := n / c
	wrappers := total
	for {
		// how many new can you get
		extra := wrappers / m
		if (extra == 0) {
			break
		}
		// give wrappers
		wrappers -= extra * m
		// receive new chocolate
		wrappers += extra
		total += extra
	}
	return total
}

func load(stdin io.Reader) {
	var t int
	fmt.Fscan(stdin, &t)
	for i := 0; i < t; i++ {
		var n, c, m int
		fmt.Fscan(stdin, &n, &c, &m)
		fmt.Println(solve(n, c, m))
	}
}

func main() {
	if len(os.Args) == 2 && os.Args[1] == "COHADAR" {
		f, err := os.Open("chocolate-feast.in")
		if err != nil {
			log.Fatal(err)
		}
		defer f.Close()
		load(f)
	} else {
		load(os.Stdin)
	}
}