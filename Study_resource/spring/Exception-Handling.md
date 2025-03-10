

|Approach |When to Use|
|---|---|
|@ExceptionHandler|Handling specific exceptions inside a controller or globally|
|@ControllerAdvice|Global exception handling for multiple controllers|
|ProblemDetail|Standardized error response structure (Spring Boot 3)|
|ResponseStatusException|Simple way to throw an HTTP error|
|MethodArgumentNotValidException|Handling validation errors (@Valid)|

## @ExceptionHandler

The @ExceptionHandler annotation is used to 
**handle exceptions inside a specific controller.**

**Basic Example:** Handling an Exception in a Controller:

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        if (id == 0) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        return new Employee(id, "John Doe", "Developer");
    }

    // Exception Handler inside the Controller
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
```

📌 **How it works:**
1.	If getEmployee(0) is called, it throws EmployeeNotFoundException.
2.	The @ExceptionHandler(EmployeeNotFoundException.class) method catches it.
3.	It returns a ResponseEntity with a 404 Not Found status and a custom message.


## @ControllerAdvice

* @ControllerAdvice is a global exception-handling mechanism in Spring Boot that allows us to 
handle exceptions across multiple controllers in a single place. 
* It helps in maintaining clean and centralized exception-handling logic, instead of writing @ExceptionHandler in each controller.

✅ Centralized exception handling for all controllers
✅ Reduces code duplication
✅ Can be combined with @ExceptionHandler for handling specific exceptions
✅ Can be used for global data binding and model attributes

```java

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

//----------------------------------EXCEPTION HANDLER WITH @RestControllerAdvice----------------------------------------------

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    ErrorResponse error = new ErrorResponse(
//            ex.getMessage(),
//            "Employee ID not found",
//            HttpStatus.NOT_FOUND.value()
//    );
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}

//--------------------------------REST COntroller------------------------------------------------------------------



@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        if (id == 0) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        return new Employee(id, "John Doe", "Developer");
    }
}

```

## ResponseStatusException

ResponseStatusException is a built-in Spring exception that allows us to throw HTTP status codes with custom error messages

✅ Quick and simple way to return HTTP status codes.
✅ No need for custom exception classes.
✅ Works well when handling specific errors inside controller methods.





