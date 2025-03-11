## Calling a RESTfull Service in Spring Boot


1. RestTemplate (Deprecated but still used in legacy applications)
2. WebClient (Recommended for non-blocking reactive applications)
3. FeignClient (Recommended for microservices communication)

### RestTemplate

**Example: Call an External API Using RestTemplate:**

```java

@Service
public class EmployeeService {

    private final RestTemplate restTemplate;

    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    //TODO: GET
    public String getEmployeeFromExternalAPI() {
        String url = "https://jsonplaceholder.typicode.com/users/1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
    
    //TODO: POST
    public ResponseEntity<String> createEmployee() {
        String url = "https://jsonplaceholder.typicode.com/posts";

        // Creating request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "Spring Boot RestTemplate");
        requestBody.put("body", "Sending POST request using RestTemplate");
        requestBody.put("userId", 1);

        // Creating headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Creating HttpEntity with body and headers
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Sending POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        return response;
    }
    
    //TODO: PUT
    public void updateEmployee() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        // Creating request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "Updated Title");
        requestBody.put("body", "Updated Content");
        requestBody.put("userId", 1);

        // Sending PUT request
        restTemplate.put(url, requestBody);
    }
    
    
}


@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

We can use **Exchange** as well :

```java
 // Sending GET request
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, null, String.class);
// Sending PUT request
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
// Sending POST request
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, requestEntity, String.class);
        
```

**getForObject() – Returns Only the Response Body** </br>
**getForEntity() – Returns Full Response (Headers + Status + Body)**

🛑 **Note: RestTemplate is being phased out, so it’s better to use WebClient for future-proofing.**



### WebClient

**Pre-Requisite :** need Webflux

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

**Build or Create a WebClient:**

```java
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com") // Default Base URL
                .defaultHeader("Content-Type", "application/json") // Default Header
                .build();
    }
    
    @Bean(name="another")
    public WebClient webClientAnother() {
        return WebClient.create("https://jsonplaceholder.typicode.com");
    }
```

**Can be used both Synchronously and Asynchronously:

**Synchronously :** Need to Block

```java
String response = webClient.post()
                .uri("/posts")
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();  // Blocking to make it synchronous
```

**ASynchronous:**

```java
    Mono<String> response = webClient.post()
                            .uri("/posts") // API Endpoint
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(requestBody)
                            .retrieve()
                            .bodyToMono(String.class);
```


#### POST :



```java
// Sending POST request
    Mono<String> output =  webClient.post()
                .uri("/posts") // API Endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
// Sending PUT request
    Mono<String> output = webClient.put()
            .uri("/posts/1") // API Endpoint
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(String.class);
    
// Sending Get Request
    Mono<String> output =  webClient.get()
            .uri("/posts/1")
            .headers(headers -> headers.setBearerAuth(token)) // Set Authorization Header
            .retrieve()
            .bodyToMono(String.class);
```

