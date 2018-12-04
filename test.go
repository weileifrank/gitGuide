package main

import "fmt"

func main() {
	a := 1
	b:= 2
	sum := add(a, b)
	fmt.Println(sum)
}
func add(a, b int) int {
	return a + b
}
