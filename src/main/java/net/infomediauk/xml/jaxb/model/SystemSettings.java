package net.infomediauk.xml.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class SystemSettings
{
  private String prospectUploadBaseUrl;

  public String getProspectUploadBaseUrl()
  {
    return prospectUploadBaseUrl;
  }

  public void setProspectUploadBaseUrl(String prospectUploadBaseUrl)
  {
    this.prospectUploadBaseUrl = prospectUploadBaseUrl;
  }
  
  
}
