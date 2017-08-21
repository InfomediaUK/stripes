package net.infomediauk.xml.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class SystemSettings
{
  private String prospectUploadBaseUrl;
  private String matchMyJobRestBaseUrl;
  private String emailFromAddress;
  private String emailProperties;
  private String emailUserName;
  private String emailPassword;

  public String getProspectUploadBaseUrl()
  {
    return prospectUploadBaseUrl;
  }

  public void setProspectUploadBaseUrl(String prospectUploadBaseUrl)
  {
    this.prospectUploadBaseUrl = prospectUploadBaseUrl;
  }

  public String getMatchMyJobRestBaseUrl()
  {
    return matchMyJobRestBaseUrl;
  }

  public void setMatchMyJobRestBaseUrl(String matchMyJobRestBaseUrl)
  {
    this.matchMyJobRestBaseUrl = matchMyJobRestBaseUrl;
  }

  public String getEmailFromAddress()
  {
    return emailFromAddress;
  }

  public void setEmailFromAddress(String emailFromAddress)
  {
    this.emailFromAddress = emailFromAddress;
  }

  public String getEmailProperties()
  {
    return emailProperties;
  }

  public void setEmailProperties(String emailProperties)
  {
    this.emailProperties = emailProperties;
  }

  public String getEmailUserName()
  {
    return emailUserName;
  }

  public void setEmailUserName(String emailUserName)
  {
    this.emailUserName = emailUserName;
  }

  public String getEmailPassword()
  {
    return emailPassword;
  }

  public void setEmailPassword(String emailPassword)
  {
    this.emailPassword = emailPassword;
  }
    
}
