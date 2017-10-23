package net.infomediauk.xml.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "firstName", "lastName", "gender", "mobileTelephone", "email", "profession", "availableForWork", 
                       "documentFileName", "disciplineId", "idDocumentId", "visaId", "lengthOfStayId"})
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
  private Integer idDocumentId;
  private Integer visaId;
  private Integer lengthOfStayId;
  
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

  public Integer getIdDocumentId()
  {
    return idDocumentId;
  }

  public void setIdDocumentId(Integer domicleId)
  {
    this.idDocumentId = domicleId;
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
    return "Prospect [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", mobileTelephone=" + mobileTelephone + ", email=" + email + ", profession=" + profession
        + ", availableForWork=" + availableForWork + ", documentFileName=" + documentFileName + ", disciplineId=" + disciplineId + ", idDocumentId=" + idDocumentId + ", visaId=" + visaId
        + ", lengthOfStayId=" + lengthOfStayId + "]";
  }
  
//  @Override
//  public String toString()
//  {
//    return String.format("%s %s %s %s", firstName, lastName, email, mobileTelephone);
//  }
}
