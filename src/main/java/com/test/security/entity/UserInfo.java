package com.test.security.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private String email;
//    private String password;
//    private String roles;
//}



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "UserInfo")
public class UserInfo {
    @Id
    private String id; // MongoDB typically uses String for ID fields as it uses ObjectId
    private String name;
    private String email;
    private String password;
    private String roles;
}
