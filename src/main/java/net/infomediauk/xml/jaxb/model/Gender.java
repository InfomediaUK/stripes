package net.infomediauk.xml.jaxb.model;

public enum Gender
{
  MALE("Male", "M"), FEMALE("Female", "F");
  private String name;
  private String code;
  private Gender(String name, String code)
  {
    this.name = name;
    this.code = code;
  }
  public String getName()
  {
    return name;
  }
  public String getCode()
  {
    return code;
  }
};
