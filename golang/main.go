package main

import (
	"database/sql"
	"fmt"
	"github.com/gin-gonic/gin"
	_ "github.com/lib/pq"
	"golang/personApp/config"
	"golang/personApp/controller"
	"golang/personApp/repo"
	"golang/personApp/service"
	"log"
)

func main() {
	cfg := config.MustLoad()
	psqlInfo := fmt.Sprintf("host=%s port=%d user=%s "+
		"password=%s dbname=%s sslmode=disable",
		cfg.Datasource.Host,
		cfg.Datasource.Port,
		cfg.Datasource.Username,
		cfg.Datasource.Password,
		cfg.Datasource.Dbname)
	db, err := sql.Open("postgres", psqlInfo)
	if err != nil {
		log.Fatal(err)
	}

	defer func(db *sql.DB) {
		err := db.Close()
		if err != nil {
			log.Fatal(err)
		}
	}(db)

	repository := repo.NewPersonRepository(db)
	serv := service.NewPersonService(repository)
	personController := controller.NewPersonController(serv)

	router := gin.Default()
	router.GET("/api/v1/person/:id", personController.GetPersonByID)
	router.POST("/api/v1/person", personController.SavePerson)

	err = router.Run(cfg.Server.Address)
	if err != nil {
		return
	}
}
