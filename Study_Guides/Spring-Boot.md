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
public interface PlayerDao extends JpaRepository<Player,Integer>{

    Optional<Player> findByPlayerName(String playerName);

    @Transactional
    @Modifying
    @Query(value = "insert into players values (default, :playerName , :playerTeamId)", nativeQuery = true)
    void createPlayer(@Param("playerName") String playerName, @Param("playerTeamId") int playerTeamId);
}
```
## Spring Web


    