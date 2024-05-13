package repo

import (
	"database/sql"
	"golang/personApp/model"
)

type PersonRepository interface {
	GetPersonByID(id int) (*model.Person, error)
	SavePerson(person *model.Person) error
}

type personRepository struct {
	db *sql.DB
}

func NewPersonRepository(db *sql.DB) PersonRepository {
	return &personRepository{db: db}
}

func (r *personRepository) GetPersonByID(id int) (*model.Person, error) {
	row := r.db.QueryRow("SELECT id, name, city, address1, address2 FROM person WHERE id = $1", id)
	person := &model.Person{}
	err := row.Scan(&person.ID, &person.Name, &person.City, &person.Address1, &person.Address2)
	if err != nil {
		return nil, err
	}
	return person, nil
}

func (r *personRepository) SavePerson(person *model.Person) error {
	_, err := r.db.Exec("INSERT INTO person (id, name, city, address1, address2) VALUES ($1, $2, $3, $4, $5) ON CONFLICT (id) DO UPDATE SET name = $2, city = $3, address1 = $4, address2 = $5", person.ID, person.Name, person.City, person.Address1, person.Address2)
	return err
}
