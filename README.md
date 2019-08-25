# registration
A spring boot console app which takes input from the user a set of domains that they want to purchase and the quantity. The price details of available domains is loaded into a in-memory H2 database. 

The project has a repository to fetch details from the database and service which fetches price details for individual domain from the database and calculates the total price for the request set of domains based on the quantity.

The user input is taken through the console in the below mentioned format. The user can continue entering as many domain requests. The calculated total price will be displayed when the user enters a blank line, which will be considered as the end of request.

e.g for user can enter multiple inputs in a new line.
```
a-domain.com,1
another-domain.net,2

Total price for the requested domain(s) is: $28
```
The project uses maven build and can be simply run from command prompt/terminal using the below command:
```
mvn spring-boot:run
```
