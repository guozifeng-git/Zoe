package router

import (
	"github.com/gin-gonic/gin"
	"net/http"
	"strings"
)

func SetUpRoute() *gin.Engine {
	router := gin.Default()
	router.GET("/", retHelloGinAndMethod)
	return router
}
func retHelloGinAndMethod(context *gin.Context) {
	context.String(http.StatusOK, "hello gin "+strings.ToLower(context.Request.Method)+" method")
}
