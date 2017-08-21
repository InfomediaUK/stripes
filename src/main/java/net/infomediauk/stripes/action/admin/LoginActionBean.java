package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlUserDao;
import net.infomediauk.model.User;
import net.infomediauk.stripes.ext.SessionActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/admin/login.htm")
public class LoginActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/login.jsp";
  private String userName;
  private String userPassword;
  private String interceptedUrl;
  private User user;
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public void setUserPassword(String userPassword)
  {
    this.userPassword = userPassword;
  }

  public String getInterceptedUrl()
  {
    return interceptedUrl;
  }

  public void setInterceptedUrl(String interceptedUrl)
  {
    this.interceptedUrl = interceptedUrl;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    return new ForwardResolution(FORM);
  }
  
  public Resolution login() throws Exception
  {
    System.out.println("In Login...");
    SessionActionBeanContext sessionActionBeanContext = getContext();
    sessionActionBeanContext.setUser(user);
    if (interceptedUrl != null)
    {
      // User originally requested this url...
      return new RedirectResolution(interceptedUrl);
    }
    return new RedirectResolution(UserListActionBean.class);
  }
  
  @ValidationMethod(on="login", priority=0)
  public void validateLogin(ValidationErrors errors)
  {
    user = XmlUserDao.getInstance().selectByName(userName);
    if (user == null)
    {
      errors.addGlobalError(new SimpleError("User NOT found."));  
    }
    else
    {
      String encryptedPassword = encryptPassword(userPassword);
      if (user.getPassword().equals(encryptedPassword))
      {
        // Password is good. Continue with login.
        System.out.println("Password is GOOD!");
      }
      else
      {
        errors.addGlobalError(new SimpleError("Password is incorrect."));
      }
    }
  }
}
