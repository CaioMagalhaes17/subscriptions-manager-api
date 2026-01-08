package com.api.app.domain.user;

import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.valueobject.Email;
import com.api.app.domain.user.valueobject.Password;

public final class UserFactory {
  public static User makeUser(String name, String email) {
    User user = new User(name, new Email(email), Password.fromPlainText("12345", new FakePasswordHasher()));
    return user;
  }
}
