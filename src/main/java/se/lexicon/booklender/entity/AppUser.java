package se.lexicon.booklender.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private String userName;
    @Column(nullable = false)
    private  String password;
    @CreationTimestamp
    private LocalDate regDate;


    //Custom constructor
    public AppUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
