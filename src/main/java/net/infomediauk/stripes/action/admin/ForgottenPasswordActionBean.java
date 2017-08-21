package net.infomediauk.stripes.action.admin;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlUserDao;
import net.infomediauk.mail.CustomMimeMessage;
import net.infomediauk.mail.SMTPAuthenticator;
import net.infomediauk.model.User;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/admin/forgottenpassword.htm")
public class ForgottenPasswordActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/forgottenPassword.jsp";
  private String email;

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String userName)
  {
    this.email = userName;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    return new ForwardResolution(FORM);
  }
  
  public Resolution sendPasswordHint() throws Exception
  {
    System.out.println("In sendPasswordHint...");
    User user = XmlUserDao.getInstance().selectByEmail(email);
    String from = "admin@matchmyjob.co.uk";
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.easily.co.uk");
    props.put("mail.smtp.port", "1025");
    props.setProperty("mail.transport.protocol", "smtp");
    props.setProperty("mail.smtp.auth", "true");
    SMTPAuthenticator authenticator = new SMTPAuthenticator("cgg0dhqh51j0", "helmet22");
    Session session = Session.getInstance(props, authenticator);
    session.setDebug(true);
    try 
    {
      Transport transport = null;
      try
      {
        transport = session.getTransport("smtp");
        // Create a message.
        MimeMessage mimeMessage = new CustomMimeMessage(session);
        // Added header stuff. 23/07/2016 Lyndon
        mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
        if (StringUtils.isEmpty(from)) 
        {
          mimeMessage.setFrom();
        } 
        else 
        {
          mimeMessage.setFrom(new InternetAddress(from));
        }
        InternetAddress[] toInternetAddresses = InternetAddress.parse(email);
        mimeMessage.addRecipients(Message.RecipientType.TO, toInternetAddresses);
        mimeMessage.setSubject("Your HINT", "UTF-8");
        mimeMessage.setText(user.getPasswordHint(), "UTF-8");
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
    catch (Exception e) 
    {
      System.out.println(e.getMessage());
    }
    return new RedirectResolution(ForgottenPasswordActionBean.class, "forgottenPasswordHintSent").addParameter("email", email);
  }
  
  @ValidationMethod(on="sendPasswordHint", priority=0)
  public void validateEmail(ValidationErrors errors)
  {
    User user = XmlUserDao.getInstance().selectByEmail(email);
    if (user == null)
    {
      errors.addGlobalError(new SimpleError("User NOT found for email address."));  
    }
  }
  
  @DontValidate
  public Resolution forgottenPasswordHintSent()
  {
    return new ForwardResolution("/WEB-INF/jsp/admin/forgottenPasswordHintSent.jsp");
  }

}
