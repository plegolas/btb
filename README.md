# btb

## Running

To have the application running you need to provide 4 arguments:

1. Product ID
 * String type, length 7 being two lower case letters followed by five digits
2. Open price
 * Float type
3. Close price (High)
 * Float type
4. Close price (Low)
 * Float type, must be lower than Close Price High.


 Example:
```
 java -jar btb.jar sd99999 10 15 5
```
