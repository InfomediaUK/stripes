package net.infomediauk.stripes.action.admin;

import net.infomediauk.dao.impl.XmlSystemSettingsDao;
import net.infomediauk.dao.impl.XmlUserDao;
import net.infomediauk.mail.MailHandler;
import net.infomediauk.model.User;
import net.infomediauk.stripes.action.BaseActionBean;
import net.infomediauk.xml.jaxb.model.SystemSettings;
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
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(LoginActionBean.class);
  }

  public Resolution sendPasswordHint() throws Exception
  {
    User user = XmlUserDao.getInstance().selectByEmail(email);
    SystemSettings systemSettings = XmlSystemSettingsDao.getInstance().select();
    String subject = "IMPORTANT - Your PJ Locums Hint";
    String text = "Your hint is: " + user.getPasswordHint();
    try
    {
      if (systemSettings.getEmailServer().equals(Constants.EMAIL_HOST))
      {
        // Use a 'normal' email host.
        MailHandler.getInstance().sendMail(user.getEmail(), subject, text);
      }
      if (systemSettings.getEmailServer().equals(Constants.EXCHANGE_SERVER))
      {
        // Use a MS Exchange Server.
        MailHandler.getInstance().sendExchangeMail(user.getEmail(), subject, text);
      }
    }
    catch (Exception e)
    {
      String errorMessage = e.getMessage();
      getContext().getValidationErrors().addGlobalError(new SimpleError(errorMessage));  
      e.printStackTrace();
      return new ForwardResolution(FORM);
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
