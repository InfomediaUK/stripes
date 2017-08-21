package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlUserDao;
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
    return new RedirectResolution(LoginActionBean.class);
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
}
