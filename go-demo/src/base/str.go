package main

import (
	"encoding/json"
	"fmt"
)

type Person struct {
	Name string
	Age  int
}

type Result struct {
	Code string `json:"code"`
	Msg  string `json:"msg"`
}

func main() {
	//var man Person
	//man.Name = "aux"
	//man.Age = 10
	//fmt.Println(man)
	//
	//woman := Person{Name: "li", Age: 20}
	//fmt.Println(woman)

	//序列化
	var res Result
	res.Code = "200"
	res.Msg = "success"
	jsons, err := json.Marshal(res)
	if err != nil {
		fmt.Println("json marshal is : ", err)
	}
	fmt.Println("json data : ", string(jsons))

	//返序列化
	var response Result
	err = json.Unmarshal(jsons, &response)
	if err != nil {
		fmt.Println("json unmarshal error:", err)
	}
	fmt.Println("res:", response)
}
