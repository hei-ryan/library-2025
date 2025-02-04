package test;

import dao.AuthorCrudOperations;
import entity.Author;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorCrudOperationsTest {
    // Always rename the class to test to 'subject'
    AuthorCrudOperations subject = new AuthorCrudOperations();

    @Test
    void read_all_authors_ok() {
        // Test for data and potential mock
        Author expectedAuthor = authorJJR();

        // Subject and the function to test
        List<Author> actual = subject.getAll(1, 3);

        // Assertions : verification to be made automatically
        assertTrue(actual.contains(expectedAuthor));
    }

    @Test
    void read_author_by_id_ok() {
        Author expectedAuthor = authorJJR();

        Author actual = subject.findById(expectedAuthor.getId());

        assertEquals(expectedAuthor, actual);
    }

    @Test
    void create_then_update_author_ok() {
        var authors = newAuthor(randomUUID().toString(), "Random famous author", LocalDate.of(2000, 1, 1));

        var actual = subject.saveAll(List.of(authors));
        //TODO: update created authors with saveAll when saveAll handle update

        var existingAuthors = subject.getAll(1,3);
        assertEquals(List.of(authors), actual);
        assertTrue(existingAuthors.containsAll(actual));
    }

    private Author authorJJR() {
        Author expectedAuthor = new Author();
        expectedAuthor.setId("author1_id");
        expectedAuthor.setName("JJR");
        expectedAuthor.setBirthDate(LocalDate.of(2000, 1, 1));
        return expectedAuthor;
    }

    private Author newAuthor(String id, String name, LocalDate birthDate) {
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setBirthDate(birthDate);
        return author;
    }
}

