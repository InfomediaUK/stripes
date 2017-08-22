package net.infomediauk.xml.jaxb.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

@XmlRootElement()
public class SystemSettings
{
  private String prospectUploadBaseUrl;
  private String matchMyJobRestBaseUrl;
  private String emailServer;
  private String emailFromAddress;
  private String emailProperties;
  private String emailUserName;
  private String emailPassword;
  private String exchangeVersion;
  private String exchangeUserName;
  private String exchangePassword;
  private String exchangeDomain;
  private Integer maxFileUploadSize;

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

  public String getEmailServer()
  {
    return emailServer;
  }

  public void setEmailServer(String emailServer)
  {
    this.emailServer = emailServer;
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
 
  public String getExchangeVersion()
  {
    return exchangeVersion;
  }

  public void setExchangeVersion(String exchangeVersion)
  {
    this.exchangeVersion = exchangeVersion;
  }

  public String getExchangeUserName()
  {
    return exchangeUserName;
  }

  public void setExchangeUserName(String exchangeUserName)
  {
    this.exchangeUserName = exchangeUserName;
  }

  public String getExchangePassword()
  {
    return exchangePassword;
  }

  public String getExchangeDomain()
  {
    return exchangeDomain;
  }

  public void setExchangeDomain(String exchangeDomain)
  {
    this.exchangeDomain = exchangeDomain;
  }

  public void setExchangePassword(String exchangePassword)
  {
    this.exchangePassword = exchangePassword;
  }

  public Properties getMailProperties()
  {
    Properties emailProperties = new Properties();
    if (StringUtils.isNotEmpty(getEmailProperties()))
    {
      try
      {
        emailProperties.load(new StringReader(getEmailProperties()));
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return emailProperties;
  }

  public Integer getMaxFileUploadSize()
  {
    return maxFileUploadSize;
  }

  public void setMaxFileUploadSize(Integer maxFileUploadSize)
  {
    this.maxFileUploadSize = maxFileUploadSize;
  }

  @Override
  public String toString()
  {
    return "SystemSettings [prospectUploadBaseUrl=" + prospectUploadBaseUrl + ", matchMyJobRestBaseUrl=" + matchMyJobRestBaseUrl + ", emailServer=" + emailServer + ", emailFromAddress="
        + emailFromAddress + ", emailProperties=" + emailProperties + ", emailUserName=" + emailUserName + ", emailPassword=" + emailPassword + ", exchangeVersion=" + exchangeVersion
        + ", exchangeUserName=" + exchangeUserName + ", exchangePassword=" + exchangePassword + ", exchangeDomain=" + exchangeDomain + ", maxFileUploadSize=" + maxFileUploadSize + "]";
  }
  
}
