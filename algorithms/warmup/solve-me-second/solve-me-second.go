package main

import (
	"os"
	"io"
	"fmt"
	"log"
)

func load(stdin io.Reader) {
	var t int
	fmt.Fscan(stdin, &t)
	for i := 0; i < t; i++ {
		var a, b int
		fmt.Fscan(stdin, &a, &b)
		fmt.Println(a + b)
	}
}

func main() {
	if len(os.Args) == 2 && os.Args[1] == "COHADAR" {
		f, err := os.Open("solve-me-second.in")
		if err != nil {
			log.Fatal(err)
		}
		defer f.Close()
		load(f)
	} else {
		load(os.Stdin)
	}
}