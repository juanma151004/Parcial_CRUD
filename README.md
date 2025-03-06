API - Person and orders Endpoints 📚

This API provides operations to manage persons in the orders system.

Database Schema:

The image shows a database schema with five tables: User, Order, OrderDetail, Product and Category. The books table contains id (book ID), title (book title), and person_id (a foreign key linking to the people table). The people table has id (person ID) and name (person's name). The relationship indicates that each book is associated with a person, likely the author.

![Postman_figure1](./assets/schema.jpg)

Postman Test:

Base URL

http://localhost:9090/demo/persons

or

http://localhost:9090/demo/books

📌 GET: Retrieve all persons

Endpoint:

List People:

GET /demo/person

![Postman_figure1](./assets/figure_1.jpg)

Description:
Retrieves a list of all people registered in the system.

GET /demo/book/id

![Postman_figure1](./assets/book.jpg)

Description:
Retrieves a book instance.

📌 POST: Create a new person

Endpoint:

POST /demo/persons

![Postman_figure1](./assets/create.jpg)

Description:
Creates a new person in the library system.

Request Body:

{
  "name": "Cristiano Ronaldo"
}

verify:

![Postman_figure1](./assets/verify.jpg)

POST /demo/books

Description:

Creates a new book in the library system.

![Postman_figure1](./assets/create_book.jpg)

Request body: 
{
    "title": "{{$randomFullName}}",
    "person_id": 4
}

Endpoint:

DELETE /demo/persons/{id}

![Postman_figure1](./assets/figure_3.jpg)

Description:
Deletes a person by their ID.

Example Request:

DELETE /demo/persons/3

Response:

{
  "message": "Person deleted successfully"
}

⚠️ Error Handling

If a request fails, the API will return an error message:

{
  "timestamp": "2025-03-02T01:30:35.755+00:00",
  "status": 404,
  "error": "Person not found",
  "path": "/demo/persons/10"
}

verify:

Person with ID=3 isnt in database

![Postman_figure1](./assets/figure_4.jpg)

Person isnt over there:


![Postman_figure1](./assets/figure_5.jpg)

🛠️ Notes

Ensure the request body is in valid JSON format.

The DELETE operation is irreversible.

Use correct IDs when making DELETE or GET requests.

🚀 Happy coding!

