package net.infomediauk.converter;

public class ConverterConstants
{
  public final static int MIN_PASSWORD_LENGTH = 6;
  public final static int MAX_PASSWORD_LENGTH = 20;
  public final static String PASSWORD_SPECIAL_CHARACTERS = "@#$%";
  public final static String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[" + PASSWORD_SPECIAL_CHARACTERS + "]).{" + MIN_PASSWORD_LENGTH + "," + MAX_PASSWORD_LENGTH + "})";

}
