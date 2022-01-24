package handler

import (
	"awesomeProject/alldemo/godemo/src/ginHello/initDB"
	"github.com/gin-gonic/gin"
	"net/http"
)

func Save(context *gin.Context) {
	userName := context.Param("name")
	initDB.Db.Exec("insert into ginhello.user (email, password) values (?,?);", context.Param("email"), context.Param("password"))

	context.String(http.StatusOK, userName)
}
