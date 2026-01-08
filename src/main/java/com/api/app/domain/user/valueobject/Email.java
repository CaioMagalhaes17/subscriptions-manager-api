package com.api.app.domain.user.valueobject;

import com.api.app.domain.user.exceptions.InvalidEmailException;

public class Email {

  private final String email;

  public Email(String email) {
    if (email == null || !email.contains("@")) {
      throw new InvalidEmailException();
    }
    this.email = email;
  }

  @Override
  public String toString() {
    return this.email;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;

    Email other = (Email) obj;
    return this.email.equals(other.email);
  }
}
