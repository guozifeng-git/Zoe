package router

import (
	"awesomeProject/alldemo/godemo/src/ginHello/handler"
	"github.com/gin-gonic/gin"
	"net/http"
)

func SetUpRoute() *gin.Engine {
	router := gin.Default()
	group := router.Group("/")
	{
		group.GET("", retHelloGinAndMethod)
	}
	router.GET("/user/:name", handler.Save)
	return router
}
func retHelloGinAndMethod(context *gin.Context) {
	context.String(http.StatusOK, "hello gin")
}
