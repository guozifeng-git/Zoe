package main

import "fmt"

func main() {
	//数组不可变

	//var variable_name [SIZE] variable_type
	var arr_1 [5]int
	fmt.Println(arr_1)

	arr_2 := [...]int{1, 2, 3, 4, 5}
	fmt.Println(len(arr_2))

	var n [10]int
	for i := 0; i < 10; i++ {
		n[i] = i + 100
	}
	for j := 0; j < 10; j++ {
		fmt.Printf("Element[%d] = %d\n", j, n[j])
	}
}
