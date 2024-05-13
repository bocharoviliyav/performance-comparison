package controller

import (
	"encoding/json"
	"github.com/gin-gonic/gin"
	"golang/personApp/model"
	"golang/personApp/service"
	"net/http"
	"strconv"
)

type PersonController struct {
	service service.PersonService
}

func NewPersonController(service service.PersonService) *PersonController {
	return &PersonController{service: service}
}

func (c *PersonController) GetPersonByID(ctx *gin.Context) {
	id := ctx.Param("id")
	if id == "" {
		http.Error(ctx.Writer, "id is nil", http.StatusInternalServerError)
		return
	}
	idInt, err := strconv.Atoi(id)
	if err != nil {
		http.Error(ctx.Writer, err.Error(), http.StatusInternalServerError)
		return
	}
	person, err := c.service.GetPersonByID(idInt)
	if err != nil {
		http.Error(ctx.Writer, err.Error(), http.StatusInternalServerError)
		return
	}
	err = json.NewEncoder(ctx.Writer).Encode(person)
	if err != nil {
		http.Error(ctx.Writer, err.Error(), http.StatusInternalServerError)
		return
	}
}

func (c *PersonController) SavePerson(ctx *gin.Context) {
	person := &model.Person{}
	err := json.NewDecoder(ctx.Request.Body).Decode(person)
	if err != nil {
		http.Error(ctx.Writer, err.Error(), http.StatusBadRequest)
		return
	}
	err = c.service.SavePerson(person)
	if err != nil {
		http.Error(ctx.Writer, err.Error(), http.StatusInternalServerError)
		return
	}
	ctx.Writer.WriteHeader(http.StatusCreated)
}
