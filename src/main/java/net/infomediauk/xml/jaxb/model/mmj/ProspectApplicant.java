package net.infomediauk.xml.jaxb.model.mmj;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.infomediauk.xml.jaxb.model.Gender;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.infomediauk.xml.jaxb.model.ProspectFile;

/**
 * This is a Prospect applicant as used by MMJ.
 * 
 * @author infomedia
 *
 */
@XmlRootElement(name="prospect")
@XmlType(propOrder = { "agencyId", "firstName", "lastName", "gender", "mobileTelephone", "email", "profession", "availableForWork", 
    "disciplineId", "idDocumentId", "visaId", "lengthOfStay", "documentFileName"})
public class ProspectApplicant
{
  private Integer agencyId;
  private String firstName;
  private String lastName;
  private Gender gender;
  private String mobileTelephone;
  private String email;
  private String profession;
  private String availableForWork;
  private Integer disciplineId;
  private Integer idDocumentId;
  private Integer visaId;
  private String lengthOfStay;
  private String documentFileName;
  
  public ProspectApplicant()
  {
    super();
  }

  public ProspectApplicant(Integer agencyId, ProspectFile prospectFile)
  {
    this.agencyId = agencyId;
    Prospect prospect = prospectFile.getProspect();
    this.setFirstName(prospect.getFirstName());
    this.setLastName(prospect.getLastName());
    this.setGender(prospect.getGender());
    this.setEmail(prospect.getEmail());
    this.setMobileTelephone(prospect.getMobileTelephone());
    this.setAvailableForWork(prospect.getAvailableForWork());
    this.setDocumentFileName(prospect.getDocumentFileName());
    this.setProfession(prospect.getProfession());
    this.setDisciplineId(prospect.getDisciplineId());
    this.setIdDocumentId(prospect.getIdDocumentId());
    this.setVisaId(prospect.getVisaId());
    this.setLengthOfStay(prospectFile.getLengthOfStayName());
  }

  public Integer getAgencyId()
  {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
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

  public Integer getIdDocumentId()
  {
    return idDocumentId;
  }

  public void setIdDocumentId(Integer idDocumentId)
  {
    this.idDocumentId = idDocumentId;
  }

  public Integer getVisaId()
  {
    return visaId;
  }

  public void setVisaId(Integer visa)
  {
    this.visaId = visa;
  }

  public String getLengthOfStay()
  {
    return lengthOfStay;
  }

  public void setLengthOfStay(String lengthOfStay)
  {
    this.lengthOfStay = lengthOfStay;
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
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("agencyId=[");
    stringBuilder.append(agencyId);
    stringBuilder.append("], ");
    stringBuilder.append("firstName=[");
    stringBuilder.append(firstName);
    stringBuilder.append("], ");
    stringBuilder.append("lastName=[");
    stringBuilder.append(lastName);
    stringBuilder.append("], ");
    stringBuilder.append("gender=[");
    stringBuilder.append(gender);
    stringBuilder.append("], ");
    stringBuilder.append("email=[");
    stringBuilder.append(email);
    stringBuilder.append("], ");
    stringBuilder.append("mobileTelephone=[");
    stringBuilder.append(mobileTelephone);
    stringBuilder.append("], ");
    stringBuilder.append("profession=[");
    stringBuilder.append(profession);
    stringBuilder.append("], ");
    stringBuilder.append("availableForWork=[");
    stringBuilder.append(availableForWork);
    stringBuilder.append("], ");
    stringBuilder.append("lengthOfStay=[");
    stringBuilder.append(lengthOfStay);
    stringBuilder.append("], ");
    stringBuilder.append("documentFileName=[");
    stringBuilder.append(documentFileName);
    stringBuilder.append("], ");
    stringBuilder.append("disciplineId=[");
    stringBuilder.append(disciplineId);
    stringBuilder.append("], ");
    stringBuilder.append("idDocumentId=[");
    stringBuilder.append(idDocumentId);
    stringBuilder.append("]");
    stringBuilder.append("visaId=[");
    stringBuilder.append(visaId);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}
