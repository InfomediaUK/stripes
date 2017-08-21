package net.infomediauk.xml.jaxb.model;

public class UserRecord extends BaseRecord
{
  private String name;
  private String email;
  private String password;
  private String passwordHint;
  
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPasswordHint()
  {
    return passwordHint;
  }

  public void setPasswordHint(String passwordHint)
  {
    this.passwordHint = passwordHint;
  }

  @Override
  public String toString()
  {
    return "UserRecord [name=" + name + ", email=" + email + ", password=" + password + ", passwordHint=" + passwordHint + "]";
  }

}
