package net.infomediauk.xml.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.infomediauk.model.ProspectShort;

@XmlRootElement
@XmlType(propOrder = { "firstName", "lastName", "gender", "mobileTelephone", "email", "profession", "availableForWork", 
                       "documentFileName", "disciplineId", "domicileId", "visaId", "lengthOfStayId"})
public class Prospect
{
  private String firstName;
  private String lastName;
  private Gender gender;
  private String mobileTelephone;
  private String email;
  private String profession;
  private String availableForWork;
  private String documentFileName;
  private Integer disciplineId;
  private Integer domicileId;
  private Integer visaId;
  private Integer lengthOfStayId;
  
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
    setMobileTelephone(prospectShort.getContactTelephone());
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

  public Gender getGender()
  {
    return gender;
  }

  public void setGender(Gender gender)
  {
    this.gender = gender;
  }

  public String getMobileTelephone()
  {
    return mobileTelephone;
  }

  public void setMobileTelephone(String contactTelephone)
  {
    this.mobileTelephone = contactTelephone;
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

  public String getAvailableForWork()
  {
    return availableForWork;
  }

  public void setAvailableForWork(String dateInCountry)
  {
    this.availableForWork = dateInCountry;
  }

  public Integer getDisciplineId()
  {
    return disciplineId;
  }

  public void setDisciplineId(Integer discipline)
  {
    this.disciplineId = discipline;
  }

  public Integer getDomicileId()
  {
    return domicileId;
  }

  public void setDomicileId(Integer domicleId)
  {
    this.domicileId = domicleId;
  }

  public Integer getVisaId()
  {
    return visaId;
  }

  public void setVisaId(Integer visa)
  {
    this.visaId = visa;
  }

  public Integer getLengthOfStayId()
  {
    return lengthOfStayId;
  }

  public void setLengthOfStayId(Integer lengthOfStayId)
  {
    this.lengthOfStayId = lengthOfStayId;
  }

  public String getDocumentFileName()
  {
    return documentFileName;
  }

  public void setDocumentFileName(String document)
  {
    this.documentFileName = document;
  }

  public String getFullName()
  {
    return String.format("%s %s", firstName, lastName);
  }
  
  @Override
  public String toString()
  {
    return String.format("%s %s %s %s", firstName, lastName, email, mobileTelephone);
  }
}
