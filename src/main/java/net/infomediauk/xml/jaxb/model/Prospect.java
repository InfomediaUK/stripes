package net.infomediauk.xml.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.infomediauk.model.ProspectShort;

@XmlRootElement
@XmlType(propOrder = { "title", "firstName", "lastName", "contactTelephone", "email", "profession", "availableForWork", 
                       "disciplineId", "domicileId", "visaId", "lengthOfStayId", "documentFileName"})
public class Prospect
{
  private String title;
  private String firstName;
  private String lastName;
  private String contactTelephone;
  private String email;
  private String profession;
  private String availableForWork;
  private Integer disciplineId;
  private Integer domicileId;
  private Integer visaId;
  private Integer lengthOfStayId;
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

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
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

  public String getTitleAndFullName()
  {
    return String.format("%s %s %s", title, firstName, lastName);
  }
  
  public String getFullName()
  {
    return String.format("%s %s", firstName, lastName);
  }
  
  @Override
  public String toString()
  {
    return String.format("%s %s %s %s %s", title, firstName, lastName, email, contactTelephone);
  }
}
