package main

import "fmt"

func main() {
	var testMap map[string]string
	testMap = make(map[string]string)
	testMap["one"] = "1"
	testMap["two"] = "2"
	fmt.Println(testMap)
}
