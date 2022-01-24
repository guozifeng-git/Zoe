package main

import "awesomeProject/alldemo/godemo/src/ginHello/router"

func main() {
	route := router.SetUpRoute()
	_ = route.Run()
}
