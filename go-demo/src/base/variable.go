package main

import "fmt"

func main() {
	//const+常量名+类型
	const name string = "tom"
	fmt.Println(name)
	//const+常量名 自动判断数据类型
	const age = 30
	fmt.Println(age)

	b := true
	fmt.Println(b)

	amount := 100
	amount = 20
	fmt.Println(amount)

}
