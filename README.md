## Intent

The is movie ticker booking application based on microservice architecture

## prerequisite
Java 11
Docker Hub
docker run --name bms-redis -p 6379:6379  -d redis

## Services


Real world example
* Booking Service: This Service create the cart for user with ticket details e.g.
```java
@Operation(summary = "create new booking")
@ApiResponses({
        @ApiResponse(responseCode = "201", description = "New cart saved successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Error occurred while processing")
})
@PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public ResponseEntity<BookShowResponse> save(@Valid @RequestBody BookShowRequest request) throws Exception {
        log.info("Adding new cart = {}", request);
        BookShowResponse response = cartService.saveCart(request);
        return new ResponseEntity<BookShowResponse>(response, HttpStatus.CREATED);
        }
```