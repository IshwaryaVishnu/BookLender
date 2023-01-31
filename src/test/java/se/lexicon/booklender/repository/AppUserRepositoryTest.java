package se.lexicon.booklender.repository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.booklender.entity.AppUser;
import se.lexicon.booklender.entity.Details;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
public class AppUserRepositoryTest {
    @Autowired
    AppUserRepository testObject;

    AppUser createdAppUser;

    @BeforeEach
    public void setup(){
        Details detailsData = new Details("ishu27.v@gmail.com","ishu", LocalDate.parse("1991-07-27"));
        AppUser appUserData = new AppUser("ishu","password", detailsData);
        createdAppUser = testObject.save(appUserData);
        assertNotNull(createdAppUser);
    }


    @Test
    public void test_findById() {
        Optional<AppUser> appUserOptional = testObject.findById(createdAppUser.getId());
        assertTrue(appUserOptional.isPresent());
        AppUser actualData = appUserOptional.get();
        AppUser expectedData = createdAppUser;
        assertEquals(expectedData, actualData);
    }
    @Test
    public void test_findByUsername(){
        Optional<AppUser> appUserOptional = testObject.findByUsername(createdAppUser.getUserName());
        assertTrue(appUserOptional.isPresent());
        AppUser actualData = appUserOptional.get();
        AppUser expectedData = createdAppUser;
        assertEquals(expectedData, actualData);

    }

    }