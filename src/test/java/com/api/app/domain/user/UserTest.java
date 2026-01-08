package com.api.app.domain.user;

import org.junit.jupiter.api.Test;

import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.exceptions.InvalidEmailException;
import com.api.app.domain.user.valueobject.Email;
import com.api.app.domain.user.valueobject.Password;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void shouldCreateUserSuccessfully() {
    Email email = new Email("user@email.com");

    User user = new User("Caio", email, Password.fromPlainText("12345", new FakePasswordHasher()));
    assertNotNull(user);
    assertNotNull(user.getId());
    assertEquals("Caio", user.getName());
    assertEquals(email, user.getEmail());
    assertNotNull(user.getCreatedAt());
    assertNull(user.getUpdatedAt());
  }

  @Test
  void shouldNotCreateUser() {
    assertThrows(InvalidEmailException.class, () -> new Email("dasdsadsa.com"));
  }
}