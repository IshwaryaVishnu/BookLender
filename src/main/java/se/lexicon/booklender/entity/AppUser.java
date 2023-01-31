package se.lexicon.booklender.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import se.lexicon.booklender.exception.DataDuplicateException;
import se.lexicon.booklender.exception.DataNotFoundException;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details details;

    @OneToMany(
            mappedBy = "borrower",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private List<BookLoan> bookLoans = new ArrayList<>();


    public AppUser(String username, String password, Details details) {
        this.username = username;
        this.password = password;
        this.details = details;
    }


    public void addBookLoan(BookLoan bookLoan) {
        if (bookLoans.contains(bookLoan)) {
            throw new DataDuplicateException("Data Duplicate Exception");
        }
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
    }

    public void removeBookLoan(BookLoan bookLoan) {
        if (!bookLoans.contains(bookLoan)) {
            throw new DataNotFoundException("Data Not Found Exception");
        }
        bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);
    }
}
