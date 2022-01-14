package main

import "fmt"

func main() {
	sli := make([]int, 0, 0)
	fmt.Println(cap(sli))
	sli = append(sli, 1, 2, 3)
	fmt.Println(sli)

	var numbers []int
	numbers = append(numbers, 1)
	numbers = append(numbers, 2)
	fmt.Println(numbers)
}
