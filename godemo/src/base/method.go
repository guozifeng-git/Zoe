package main

import "fmt"

func main() {
	number := max_number(10, 20)
	fmt.Println(number)
	s, s2 := swap("push", "pull")
	fmt.Println(s, s2)
}

func max_number(num, num2 int) int {
	var result int
	if num > num2 {
		result = num
	} else {
		result = num2
	}
	return result
}

func swap(x, y string) (string, string) {
	return y, x
}
