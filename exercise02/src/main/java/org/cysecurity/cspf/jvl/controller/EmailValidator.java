package org.cysecurity.cspf.jvl.controller;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailValidator {

  public boolean isValidEmailAddress(String email) {
    boolean result = true;

    try {
      InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
    } catch (AddressException ex) {
      result = false;
    }
    return result;
  }

}