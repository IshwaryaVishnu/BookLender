package se.lexicon.booklender.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Data // Setter,Getter,ToString,Equal & Hashcode +Required constructor
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private  String email;
    @Column(nullable = false)
    private  String name;
    private LocalDate birthDate;

    public Details(String email, String name, LocalDate birthDate) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }
}
