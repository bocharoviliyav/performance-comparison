package service

import (
	"golang/personApp/model"
	"golang/personApp/repo"
)

type PersonService interface {
	GetPersonByID(id int) (*model.Person, error)
	SavePerson(person *model.Person) error
}

type personService struct {
	repo repo.PersonRepository
}

func NewPersonService(repo repo.PersonRepository) PersonService {
	return &personService{repo: repo}
}

func (s *personService) GetPersonByID(id int) (*model.Person, error) {
	return s.repo.GetPersonByID(id)
}

func (s *personService) SavePerson(person *model.Person) error {
	return s.repo.SavePerson(person)
}
