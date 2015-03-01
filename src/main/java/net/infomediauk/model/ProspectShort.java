package net.infomediauk.model;

public class ProspectShort
{
  private String fullName;
  private String contactTelephone;
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

  public String getContactTelephone()
  {
    return contactTelephone;
  }

  public void setContactTelephone(String contactTelephone)
  {
    this.contactTelephone = contactTelephone;
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
    contactTelephone = null;
    email = null;
    profession = null;
  }

}
