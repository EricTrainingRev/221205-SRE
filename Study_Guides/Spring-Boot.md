# Spring Boot
Spring Boot is an opinionated implementation of the Spring framework. It makes use of the many modules provided by Spring and auto-configures them for you. We've explored two modules so far: Spring Data and Spring Web

## Spring Data
With Spring Data we are able to "map" our Entity objects to tables within our database. Spring uses a tool called "hibernate" to do this. We use a couple annotations in our Spring Boot application to tell Spring how to map the class with the table. Note that the various annotations provided below come from different sources

### javax.persistence package (Not provided by Spring)
- @Entity
    - this annotation indicates the class is an entity that needs to be mapped with a table in the database
- @Table
    - this annotation lets us specify the table the entity should be mapped to
- @Id
    - this annotation indicates which field represents the primary key column in the table
- @Column
    - this annotation lets us specify what column the field should be mapped to: this is necessary if the field name does not match the column name (like if different naming conventions are used)

```java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @Column(name = "person_id")
    private int personId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    // getters, setters, toString, etc...

}
```

### org.springframework.data
- JpaRepository<> interface
    - JpaRepository provides built in methods that are designed to work with whatever entity and primary key type you provide as generics
        - JpaRepository\<Person,Integer\>
        - findById() returns a record from the mapped table with the same id
        - findAll() returns all records from the mapped table
    - the interface also provides a way of creating custom methods that will have their implementation defined at runtime
        - if your class has a field called "firstName" you could define a method called "findByFirstname(String firstname)" and Spring will be able to implement the method at runtime
- @Query
    - lets us indicate what query we want executed when the method is called. If using native SQL (think PostgreSQL) you must indicate it
- @Modifying
    - tells Spring that the method is not making use of default settings provided by Spring Data. Must be used if @Query is making a DML (insert, update, delete) transaction
- @Transactional
    - tells Spring that the method is expected to commit a transaction in the databse. Used alongside @Modifying and @Query

```Java
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

// note that no annotation is needed for the class in the repository layer
public interface PlayerDao extends JpaRepository<Player,Integer>{

    Optional<Player> findByPlayerName(String playerName);

    @Transactional
    @Modifying
    @Query(value = "insert into players values (default, :playerName , :playerTeamId)", nativeQuery = true)
    void createPlayer(@Param("playerName") String playerName, @Param("playerTeamId") int playerTeamId);
}
```
## Spring Web
Formerly known as Spring MVC, Spring Web allows us to create an API for our application that can handle incoming HTTP requests and returning HTTP responses. Some of the annotations to know:
- @RestController
    - tells Spring that the class will be used to handle incoming HTTP requests and outgoing HTTP responses
- mapping annotations
    - @GetMapping("/url")
    - @PostMapping("/url")
    - @PutMapping("/url")
    - @DeleteMapping("/url")
        - indicates the method annotated will handle http requests with the given verb and url
        - urls can contain path variables, indicated between curly brackets
- @ExceptionHandler
    - tells spring to use the annotated method to handle the HTTP response when an exception is thrown in the controller class
- @PathVariable
    - tells Spring to take assign the value of the path variable to the annotated parameter
- @RequestBody
    - tells spring to convert the body of the HTTP request to the Java type provided as a parameter
- ResponseEntity
    - a class that provides a convinient means of setting the body and status code of the HTTP response
        - takes a generic to indicate what type is being returned in the response
        - HttpStatus is used to indicate what status code to return


```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

     @ExceptionHandler(SomeException.class)
     public ResponseEntity<String> playerNotfound(SomeException e){
         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
     }

    @GetMapping("/player/{pathVariable}")
    public ResponseEntity<Object> findById(@PathVariable int pathVariable){
        return new ResponseEntity<>(this.playerService.getPlayerById(pathVariable),HttpStatus.OK);
    }    

    @PostMapping("/player")
    public ResponseEntity<String> createPlayer(@RequestBody Player player){
        return new ResponseEntity<>(this.playerService.createPlayer(player), HttpStatus.CREATED);
    }

    // delte mappings, put mappings, etc
}

```

## Handler Interceptors
Sometimes you will need your code to execute before and/or after a controller method: this is where Handler interceptors come into play. 
- HandlerInterceptor
    - this interface provides access to the various HTTP objects we need for interacting with requests and responses
- @Component
    - this tells Spring it needs to manage the class
- preHandle
    - this method is executed before the method mapped to the request is executed. If it returns false then the mapped method is not executed
- postHandle
    - this method is executed after the method mapped to the request is finished. Will not trigger if an exception is thrown during the mapped method execution
- afterCompletion
    - this method is exectued after the method mapped to the request is handled, including if an exceptin is raised.
- WebMVCConfigurer
    - an interface that provides the means of "registering" handler interceptors
- @Configuration
    - tells Spring this class changes the default configurations of the application
- addInterceptors
    - a method that is used to tell Spring what interceptors to register
- InterceptorRegistry
    - the class that keeps track of interceptors, its paramter is referenced in the addInterceptors method
        - addInterceptor
            - registers the interceptor provided as an argument to the interceptor registry
        - addPathPatterns
            - tells Spring what url patterns should be intercepted by the handler interceptor
        - order
            - tells Spring what priority the interceptor should have. The lower the priority number the higher priority the interceptor has
                - if you have two interceptors, one priority 0, the other priority 1, the interceptor with priority 0 will have its preHandle execute first

```java
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component // this tells Spring to manage the class
public class BasicInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       System.out.println("BasicInterceptor preHandle executed");
       return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("BasicInterceptor postHandle executed");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("BasicInterceptor afterCompletion executed");
    } 
```
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer { // Spring Web is the new name for Spring MVC
    
    @Autowired
    private BasicInterceptor basicInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(basicInterceptor).addPathPatterns("/**").order(Ordered.LOWEST_PRECEDENCE);
        
    }
```


    