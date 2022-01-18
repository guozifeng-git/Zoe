// 官方 Demo
//快速入门
//https://gin-gonic.com/zh-cn/docs/quickstart/
package main

import "github.com/gin-gonic/gin"

func main() {
	r := setUpRoute()
	err := r.Run()
	if err != nil {
		return
	} // listen and serve on 0.0.0.0:8080
}

func setUpRoute() *gin.Engine {
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})
	return r
}
