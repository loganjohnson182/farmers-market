# farmers-market

This service will calculate the price of items in a farmers market basket. The following items are available:

```
+--------------|--------------|---------+
| Product Code |     Name     |  Price  |
+--------------|--------------|---------+
|     CH1      |   Chai       |  $3.11  |
|     AP1      |   Apples     |  $6.00  |
|     CF1      |   Coffee     | $11.23  |
|     MK1      |   Milk       |  $4.75  |
|     OM1      |   Oatmeal    |  $3.69  |
+--------------|--------------|---------+
```

## Installation

Build the project using maven either within an IDE or running the following command:

```
mvn clean install
```

## Usage

Start the service using the following command:

```
mvn spring-boot:run
```

Navigate to the following URL:

```
http://localhost:8080/swagger-ui.html
```

Using the Swagger interface provide a list of Product Codes above.

Sample Request:

```
{
  "basket": [
    "CH1","AP1"
  ]
}
```

Sample Response:

```
{
  "totalPriceExpected": "9.11"
}
```
