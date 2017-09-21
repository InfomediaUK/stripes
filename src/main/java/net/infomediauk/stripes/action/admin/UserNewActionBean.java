package net.infomediauk.stripes.action.admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.infomediauk.converter.ConverterConstants;
import net.infomediauk.dao.impl.XmlUserDao;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class UserNewActionBean extends UserActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/userNew.jsp";
  private String password;
  private String confirmPassword;
  private String passwordHint;

  public String getPassword()
  {
    return password;
  }

  @Validate(required=true, minlength=ConverterConstants.MIN_PASSWORD_LENGTH, maxlength=ConverterConstants.MAX_PASSWORD_LENGTH)
  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getConfirmPassword()
  {
    return confirmPassword;
  }

  @Validate(required=true, minlength=ConverterConstants.MIN_PASSWORD_LENGTH, maxlength=ConverterConstants.MAX_PASSWORD_LENGTH)
  public void setConfirmPassword(String confirmPassword)
  {
    this.confirmPassword = confirmPassword;
  }

  public String getPasswordHint()
  {
    return passwordHint;
  }

  @Validate(required=true)
  public void setPasswordHint(String passwordHint)
  {
    this.passwordHint = passwordHint;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("New User");
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlUserDao.getInstance().backupDatabase();
    XmlUserDao.getInstance().update(getUser());
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", getUser().getName()));
    return new RedirectResolution(UserListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(UserListActionBean.class);
  }

  @ValidationMethod(on="save", priority=0)
  public void validateNameAndPassword(ValidationErrors errors)
  {
    String name = getUser().getName();
    if (XmlUserDao.getInstance().selectByName(name) != null)
    {
      errors.addGlobalError(new SimpleError(name + " is already taken."));
    }
    if (!password.equals(confirmPassword))
    {
      errors.addGlobalError(new SimpleError("The Passwords don\'t match."));  
    }
  }
  
  /**
   * The password should already be validated for acceptable length and that it matches the confirm password.
   * 
   * @param errors
   */
  @ValidationMethod(on="save", priority=99)
  public void validatePasswordContainsSpecialCharacter(ValidationErrors errors)
  {
    Pattern pattern = Pattern.compile(ConverterConstants.PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    if (matcher.matches())
    {
      getUser().setPassword(encryptPassword(password));
    }
    else
    {
      errors.addGlobalError(new SimpleError("The password must be " + ConverterConstants.MIN_PASSWORD_LENGTH + " to " + ConverterConstants.MAX_PASSWORD_LENGTH + " characters in length with at least one digit, one upper case letter, one lower case letter and one special symbol: " + ConverterConstants.PASSWORD_SPECIAL_CHARACTERS));  
    }
  }
}
