package net.infomediauk.xml.jaxb.model.mmj;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.infomediauk.xml.jaxb.model.Prospect;
import net.infomediauk.xml.jaxb.model.ProspectFile;

/**
 * This is a Prospect applicant as used by MMJ.
 * 
 * @author infomedia
 *
 */
@XmlRootElement(name="prospect")
@XmlType(propOrder = { "title", "firstName", "lastName", "contactTelephone", "email", "profession", "availableForWork", 
                       "domicile", "lengthOfStay", "documentFileName", "disciplineId", "visaId"})
public class ProspectApplicant
{
  private String title;
  private String firstName;
  private String lastName;
  private String contactTelephone;
  private String email;
  private String profession;
  private String availableForWork;
  private String domicile;
  private String lengthOfStay;
  private String documentFileName;
  private Integer disciplineId;
  private Integer visaId;
  
  public ProspectApplicant()
  {
    super();
  }

  public ProspectApplicant(ProspectFile prospectFile)
  {
    Prospect prospect = prospectFile.getProspect();
    this.setTitle(prospect.getTitle());
    this.setFirstName(prospect.getFirstName());
    this.setLastName(prospect.getLastName());
    this.setEmail(prospect.getEmail());
    this.setContactTelephone(prospect.getContactTelephone());
    this.setAvailableForWork(prospect.getAvailableForWork());
    this.setDocumentFileName(prospect.getDocumentFileName());
    this.setProfession(prospect.getProfession());
    this.setDisciplineId(prospect.getDisciplineId());
    this.setVisaId(prospect.getVisaId());
    this.setDomicile(prospectFile.getDomicileName());
    this.setLengthOfStay(prospectFile.getLengthOfStayName());
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

  public String getDomicile()
  {
    return domicile;
  }

  public void setDomicile(String domicile)
  {
    this.domicile = domicile;
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
