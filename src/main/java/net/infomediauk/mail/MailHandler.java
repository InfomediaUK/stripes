package net.infomediauk.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import net.infomediauk.dao.impl.XmlSystemSettingsDao;
import net.infomediauk.xml.jaxb.model.SystemSettings;

import org.apache.commons.lang3.StringUtils;


public class MailHandler
{
  private static MailHandler instance = null;

  public static MailHandler getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(MailHandler.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new MailHandler();
        }
      }
    }
    return instance;
  }

  public void sendMail(String to, String subject, String text) throws Exception
  {
    SystemSettings systemSettings = XmlSystemSettingsDao.getInstance().select();
    String from = systemSettings.getEmailFromAddress();
    Properties mailProperties = systemSettings.getMailProperties();;
    SMTPAuthenticator authenticator = new SMTPAuthenticator(systemSettings.getEmailUserName(), systemSettings.getEmailPassword());
    Session session = Session.getInstance(mailProperties, authenticator);
    session.setDebug(true);
    Transport transport = null;
    try
    {
      transport = session.getTransport("smtp");
      // Create a message.
      MimeMessage mimeMessage = new CustomMimeMessage(session);
      mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
      mimeMessage.addHeader("format", "flowed");
      mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
      if (StringUtils.isEmpty(from)) 
      {
        mimeMessage.setFrom();
      } 
      else 
      {
        mimeMessage.setFrom(new InternetAddress(from));
      }
      InternetAddress[] toInternetAddresses = InternetAddress.parse(to);
      mimeMessage.addRecipients(Message.RecipientType.TO, toInternetAddresses);
      mimeMessage.setSubject(subject, "UTF-8");
      mimeMessage.setText(text, "UTF-8");
      mimeMessage.setSentDate(new java.util.Date());
      transport.connect();
      // Send the mail.
      transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
    }
    finally
    {
      if (transport != null)
      {
        transport.close();
      }
    }
  }
  
  static class RedirectionUrlCallback implements IAutodiscoverRedirectionUrl 
  {
    public boolean autodiscoverRedirectionUrlValidationCallback(String redirectionUrl) 
    {
        return redirectionUrl.toLowerCase().startsWith("https://");
    }
  }
  
  public void sendExchangeMail(String to, String subject, String text) throws Exception
  {
    SystemSettings systemSettings = XmlSystemSettingsDao.getInstance().select();
    String exchangeUserName = systemSettings.getExchangeUserName();
    String exchangePassword = systemSettings.getExchangePassword();
    ExchangeVersion exchangeVersion = ExchangeVersion.valueOf(systemSettings.getExchangeVersion());
    ExchangeService service = new ExchangeService(exchangeVersion);
    ExchangeCredentials credentials = new WebCredentials(exchangeUserName, exchangePassword, systemSettings.getExchangeDomain());
    service.setCredentials(credentials);    
    service.autodiscoverUrl(exchangeUserName, new RedirectionUrlCallback());
    EmailMessage emailMessage = new EmailMessage(service);
    emailMessage.setSubject(subject);
    emailMessage.setBody(MessageBody.getMessageBodyFromText(text));
    emailMessage.getToRecipients().add(to);
    emailMessage.send();    
  }
}