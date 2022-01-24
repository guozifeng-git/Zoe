package initDB

import (
	"database/sql"
	"log"
)

var Db *sql.DB

func Init() {
	var err error
	//database, err := sql.Open("数据库类型", "用户名:密码@tcp(地址:端口)/数据库名")
	Db, err = sql.Open("mysql", "root:123456@tcp(127.0.0.1:3306)/ginhello")
	if err != nil {
		log.Panicln("err:", err.Error())
	}
	Db.SetMaxOpenConns(10)
	Db.SetMaxIdleConns(10)
}
