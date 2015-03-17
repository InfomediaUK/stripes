package net.infomediauk.xml.jaxb.model;

public enum Gender
{
  MALE("Male"), FEMALE("Female");
  private String description;
  private Gender(String description)
  {
    this.description = description;
  }
  public String getDescription()
  {
    return description;
  }
};
