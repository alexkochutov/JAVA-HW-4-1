package ru.netology.exceptions;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {
    Repository repository = new Repository();
    Product item1 = new Product(1, "product", 1000);
    Product item2 = new Book(2, "book", 100, "author");
    Product item3 = new Smartphone(3, "smartphone", 100000, "apple");

    @Test
    void shouldRemoveByID() {
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);
        repository.removeByID(1);

        Product[] expected = {item2, item3};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnException() {
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);

        assertThrows(NotFoundException.class, () -> repository.removeByID(4));
    }

}