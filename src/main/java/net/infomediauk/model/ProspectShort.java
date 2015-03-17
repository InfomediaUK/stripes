package net.infomediauk.model;

public class ProspectShort
{
  private String fullName;
  private String mobileTelephone;
  private String email;
  private String profession;
    
  public String getFullName()
  {
    return fullName;
  }

  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  public String getMobileTelephone()
  {
    return mobileTelephone;
  }

  public void setMobileTelephone(String mobileTelephone)
  {
    this.mobileTelephone = mobileTelephone;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getProfession()
  {
    return profession;
  }

  public void setProfession(String profession)
  {
    this.profession = profession;
  }
  
  public void clear()
  {
    fullName = null;
    mobileTelephone = null;
    email = null;
    profession = null;
  }

}
