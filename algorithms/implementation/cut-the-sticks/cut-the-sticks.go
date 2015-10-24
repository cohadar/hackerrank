package main

import "os"
import "fmt"
import "bufio"

func load(reader : bufio.Reader) {

}

func main() {
	if (len(os.Args) == 2 && os.Args[1] == "COHADAR") {
    	fmt.Println("hello COHADAR")
    	fi, _ := os.Open("cut-the-sticks.in")
   		reader := bufio.NewReader(fi)
    } else {
    	fmt.Println("hello world")
    	reader := bufio.NewReader(os.Stdin)
    }
}
