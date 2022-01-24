package model

import (
	"awesomeProject/alldemo/godemo/src/ginHello/initDB"
	"log"
)

type UserModel struct {
	Email         string `json:"email"`
	Password      string `json:"password"`
	PasswordAgain string `json:"password-again"`
}

func (user *UserModel) Save() int64 {
	result, err := initDB.Db.Exec("insert into ginhello.user (email, password) values (?,?);", user.Email, user.Password)
	if err != nil {
		log.Panicln("err:", err.Error())
	}
	id, err := result.LastInsertId()
	if err != nil {
		log.Panicln("err:", err.Error())
	}
	return id
}
