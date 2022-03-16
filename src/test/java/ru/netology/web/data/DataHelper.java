package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
  private DataHelper() {}

  @Value
  public static class AuthInfo {
    private String login;
    private String password;
    private String number;
    private int balance;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123", "5559 0000 0000 0001", 10000);
  }

  public static AuthInfo getOtherAuthInfo(AuthInfo original) {
    return new AuthInfo("vasya", "qwerty123", "5559 0000 0000 0002", 10000);
  }

  @Value
  public static class VerificationCode {
    private String code;
  }

  public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
    return new VerificationCode("12345");
  }
}




