# Product Reccomender
A product recommendation system without using any mutable variables or state, built with Scala and JavaFx GUI. For this project, I used freely available data from the UCI Machine Learning Repository containing purchase information from an online retailer.

## Functionality
* The system will use the data to decide which items should be recommended to a new customer, for whom we have no data, who is viewing a given item. 
* This will be computed by choosing the item from the data that is most correlated with the item being viewed. 
* This will be a measure of how often customers who purchase this item also purchase the other items (ie. A high correlation indicates that those items are often purchased together).
