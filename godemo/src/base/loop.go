package main

import "fmt"

func main() {
	sli := [3]string{"xx", "zz", "yy"}
	for i := range sli {
		fmt.Println(sli[i])
	}
	for key, value := range sli {
		fmt.Println("sli[%d]:\n", key, value)
	}
	for i := 0; i < 10; i++ {
		if i == 6 {
			fmt.Println(i)
			goto END
		}
	}

END:
	fmt.Println("end")
}
