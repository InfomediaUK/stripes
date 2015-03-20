package net.infomediauk.xml.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class SystemSettings
{
  private String prospectUploadBaseUrl;
  private String matchMyJobRestBaseUrl;

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
    
}
