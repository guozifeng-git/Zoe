package handler

import (
	"awesomeProject/alldemo/godemo/src/ginHello/model"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

func UserRegister(context *gin.Context) {
	var user model.UserModel
	if err := context.ShouldBind(&user); err != nil {
		context.String(http.StatusBadRequest, "输入的数据不合法")
		log.Panicln("err ->", err.Error())
	}
	id := user.Save()
	log.Println("id is:", id)
	context.Redirect(http.StatusOK, "/")
}
