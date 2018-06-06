# RestUrl

## Running the app:
- Run the war file on your local server, war file inside **target** directoy
- OR open the project in your IDE ( Intellij, Eclipse ) and run the RestUrlApplication.java

## Using:

#### You can use [Postman](https://www.getpostman.com/) to handle requests.

### Creating a user 
- send a POST request to **localhost:8080/account** with Request Type 'application/json' with **username** parameter
- example: { "username":"BionicBarry" }
- the request returns your newly generated password

### See existing users 
- send a GET request to **localhost:8080/users**
- the request lists all existing users

### Generate short URL
- send a POST request to **localhost:8080/register** with Request Type 'application/json' with **longUrl** parameter
- example: { "longUrl":"https://SomeReallyReally/Really/Really/LongUrl.com" }
- the request returns the generated shortUrl ( example: localhost:8080/Xj8J7k )

### Redirect using shortUrl
- send a GET request to the shorUrl and you will be redirected to it's longUrl pair

### See all existing URL
- send a GET request to **localhost:8080/links**
- the request returns all existing links

### See visit statistic
- send GET request to **localhost:8080/statistic/{username}** where username is a username of any existing user
- the request returns every longUrl with the number of visits it has
- *note* : User Authentication wasn't implemented

## Dependencies
- Check the [pom.xml](https://github.com/Domogo/RestUrl/blob/master/pom.xml)
               
