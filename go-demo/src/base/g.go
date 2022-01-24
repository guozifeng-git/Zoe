package main

import (
	"fmt"
	"time"
)

func main() {
	fmt.Println("main start")
	//在 go 关键字后面加一个函数，就可以创建一个线程，函数可以为已经写好的函数，也可以是匿名函数。
	go func() {
		fmt.Println("goroutine")
	}()
	time.Sleep(1 * time.Second)
	fmt.Println("main end")
}
