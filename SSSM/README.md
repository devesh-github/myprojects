# Super Simple Stock Market
Coding Assignment – Super Simple Stock Market

#Requirements

* Provide working source code that will:-
  * For a given stock, 
    * Given any price as input, calculate the dividend yield
    * Given any price as input, calculate the P/E Ratio
    * Record a trade, with timestamp, quantity of shares, buy or sell indicator and 
    * Calculate Volume Weighted Stock Price based on trades in past 15 minutes
  * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks traded price

#Constraints & Notes

* Written in one of these languages: Java, C#, C++, Python
* No database or GUI is required, all data need only be held in memory
* No prior knowledge of stock markets or trading is required – all formulas are provided below.

#Features of the Project
* It uses Spring Boot and Java 8. 
* It is a Server App running on port 8080, having the REST APIs for the required 5 functions given in Requirements
* It is integrated with Swagger which enables the User to run the APIs from Browser
* Sample data from the Global Beverage Corporation Exchange is Added via the application.properties

#How to use:
This is a maven project, so you can run these 2 goals:
* mvn test -> to execute the unit tests.
* mvn package -> to generate the executable jar.

To run the program just run:
* java -jar super-simple-stock-market-0.1.0.jar

To run the REST APIs
* After the Server is started by the above command. Please open http://localhost:8080/swagger-ui.html# on Browser
* Now we can run the APIs by filling appropriate values needed for the APIs


#Controller Classes
* StockMarketController -> Controller for Calculating GBCE API
* StockController -> Controller for Calculating Dvident Yield, PE Ratio, Vol Weighted  values for given Stock
* TradeController -> Controller for Adding Trades

# Test Controller Classes
* StockMarketControllerTest
* StockControllerTest
* TradeControllerTest

