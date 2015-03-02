package net.infomediauk.model;

public class Prospect
{
  private String firstName;
  private String lastName;
  private String contactTelephone;
  private String email;
  private String profession;
  private Integer disciplineId;
  private Integer domicleId;
  private Integer stayId;
  private Integer visaId;
  private String documentFileName;
  
  public Prospect()
  {
    super();
  }
  
  public Prospect(ProspectShort prospectShort)
  {
    int spaceIndex = prospectShort.getFullName().indexOf(" ");
    if (spaceIndex == -1)
    {
      setFirstName(prospectShort.getFullName());
    }
    else
    {
      setFirstName(prospectShort.getFullName().substring(0, spaceIndex));
      setLastName(prospectShort.getFullName().substring(spaceIndex + 1));
    }
    setContactTelephone(prospectShort.getContactTelephone());
    setEmail(prospectShort.getEmail());
    setProfession(prospectShort.getProfession());
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
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

  public Integer getDisciplineId()
  {
    return disciplineId;
  }

  public void setDisciplineId(Integer discipline)
  {
    this.disciplineId = discipline;
  }

  public Integer getDomicleId()
  {
    return domicleId;
  }

  public void setDomicleId(Integer domicleId)
  {
    this.domicleId = domicleId;
  }

  public Integer getStayId()
  {
    return stayId;
  }

  public void setStayId(Integer stayId)
  {
    this.stayId = stayId;
  }

  public Integer getVisaId()
  {
    return visaId;
  }

  public void setVisaId(Integer visa)
  {
    this.visaId = visa;
  }

  public String getDocumentFileName()
  {
    return documentFileName;
  }

  public void setDocumentFileName(String document)
  {
    this.documentFileName = document;
  }
  
}
