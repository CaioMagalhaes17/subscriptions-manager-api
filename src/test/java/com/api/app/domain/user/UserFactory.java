package com.api.app.domain.user;

import com.api.app.domain.user.entity.User;
import com.api.app.domain.user.valueobject.Email;

public final class UserFactory {
  public static User makeUser(String name, String email) {
    User user = new User(name, new Email(email));
    return user;
  }
}
