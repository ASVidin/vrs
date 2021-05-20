Graduation online project based on Topjava
===============================
_______________________________
###The task is:

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we assume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.
_______________________________
### Implementation Stack:

- Maven
- Java 8
- Spring (Data JPA, Boot, Data REST, Security)
- Hibernate
- SLF4J, Logback
- H2
- JUnit

### API:
####Entities
User: id, name, email, password, roles
Restaurant: id, name; dishes
DailyMenu: id, inputDate, restaurant
Dish: id, name, price, dailyMenu
Vote: id, voteDate, fromUser, restaurant

###Requests

format: <HTTP_Type> [Params] [(Body)] [:<Response_Type>]

For all requests authorization is required. Basic Authorization (email + password) is supported.

#### Requests for user
- Get user: Get /rest/users : User
<pre>curl -s http://localhost:8080/rest/users --user user@gmail.com:password</pre>

- Update user: Put /rest/users (User)
<pre>
curl -s -X PUT -d '{"id":1,"name":"newUser","email":"newEmail@gmail.com","password":"newPass"}' -H 'Content-Type:application/json' http:/localhost:8080/rest/users --user user@gmail.com:password
</pre>

- Registration user: Post /rest/users/register (User): User
<pre>
curl -s -X POST -d '{"name" : "New User", "email" : "registered@mail.ru", "password" : "register"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/users/register
</pre>

- Delete user: Delete /rest/users
<pre>
curl -s -X DELETE http://localhost:8080/rest/users/ --user user@gmail.com:password
</pre>

- Vote for restaurant: Post /rest/votes/id: Vote
<pre>
curl 'http://localhost:8080/rest/votes/1' -i -X POST -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ=' -H'Content-Type: application/json'
</pre>

- Get restaurant with menu: Get /rest/restaurants/id : (Restaurants)
<pre>
curl -s http://localhost:8080/rest/restaurants/1 --user user@gmail.com:password
</pre>


#### Requests for admin
- Create restaurant: Post /rest/restaurants (Restaurant) : Restaurant
<pre>
curl -s -X POST -d '{"name" : "Friends and Family"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurants --user admin@ya.ru:admin
</pre>

- Update restaurant: Put /rest/restaurants/id (Restaurant)
<pre>
$ curl -s -X PUT -d '{"id" : "1", "name" : "New Rest"}' -H 'Content-Type:application/json' http://localhost:8080/rest/restaurants/1 --user admin@ya.ru:admin
</pre>

- Delete restaurants: Delete /rest/restaurants/id
<pre>
curl -s -X DELETE http://localhost:8080/rest/restaurants/1 --user admin@ya.ru:admin
</pre>

- Create dish for restaurant: Post /rest/restaurants/restaurantId/dishes (Dish) : Dish
<pre>
curl -s -X POST -d '{"name" : "newDish", "price" : "333"}' -H 'Content-Type:application/json' http://localhost:8080/rest/restaurants/2/dishes/ --user admin@ya.ru:admin
</pre>

- Create menu for restaurant: Post /rest/restaurants/restaurantId/menus (DailyMenu) : DailyMenu
<pre>
curl -s -X POST -d '{"date" : "2021-05-20"}' -H 'Content-Type:application/json' http://localhost:8080/rest/restaurants/2/menus/ --user admin@ya.ru:admin
</pre>

-Update menu for restaurant: Put /rest/restaurants/restaurantId/menus/id (DailyMenu)
<pre>
curl -s -X PUT -d '{"id" : "3", "date" : "2021-05-21"}' -H 'Content-Type:application/json' http://localhost:8080/rest/restaurants/3/menus/3 --user admin@ya.ru:admin
</pre>

- Delete restaurants: Delete /rest/restaurants/restaurantId/menus/id
<pre>
curl -s -X DELETE http://localhost:8080/rest/restaurants/3/menus/3 --user admin@ya.ru:admin
</pre>