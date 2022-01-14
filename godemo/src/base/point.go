package main

import "fmt"

func main() {
	//&取地址
	a := "xxx"
	fmt.Println(&a)
	value := 10
	var p *int
	p = &value

	fmt.Println(&value)
	fmt.Println(p)
	fmt.Println(*p)

}
