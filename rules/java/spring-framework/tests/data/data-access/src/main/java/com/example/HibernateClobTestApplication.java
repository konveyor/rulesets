package com.example;

import org.hibernate.dialect.PostgreSQL81Dialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Clob;

@SpringBootApplication
public class HibernateClobTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateClobTestApplication.class, args);
    }
}

@Entity
class MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String largeText;

    private Clob clobData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLargeText() {
        return largeText;
    }

    public void setLargeText(String largeText) {
        this.largeText = largeText;
    }

    public Clob getClobData() {
        return clobData;
    }

    public void setClobData(Clob clobData) {
        this.clobData = clobData;
    }
}

@Repository
interface MyEntityRepository extends JpaRepository<MyEntity, Long> {

}