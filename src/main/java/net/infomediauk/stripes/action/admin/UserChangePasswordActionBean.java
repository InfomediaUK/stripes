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

public class UserChangePasswordActionBean extends UserActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/userChangePassword.jsp";
  private String oldPassword;
  private String newPassword;
  private String confirmPassword;
  private String passwordHint;

  public String getOldPassword()
  {
    return oldPassword;
  }

  @Validate(required=true)
  public void setOldPassword(String oldPassword)
  {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword()
  {
    return newPassword;
  }

  @Validate(required=true, minlength=ConverterConstants.MIN_PASSWORD_LENGTH, maxlength=ConverterConstants.MAX_PASSWORD_LENGTH)
  public void setNewPassword(String newPassword)
  {
    this.newPassword = newPassword;
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

  @Validate(required=true)
  public String getPasswordHint()
  {
    return passwordHint;
  }

  public void setPasswordHint(String passwordHint)
  {
    this.passwordHint = passwordHint;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    setUser(XmlUserDao.getInstance().select(getUser().getId()));
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlUserDao.getInstance().backupDatabase();
    setUser(XmlUserDao.getInstance().select(getUser().getId()));
    getUser().setPassword(encryptPassword(newPassword));
    getUser().setPasswordHint(passwordHint);
    XmlUserDao.getInstance().updatePassword(getUser());
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", getUser().getName()));
    return new RedirectResolution(UserListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(UserEditActionBean.class).addParameter("user.id", getUser().getId());
  }

  @ValidationMethod(on="save", priority=0)
  public void validatePasswords(ValidationErrors errors)
  {
    setUser(XmlUserDao.getInstance().select(getUser().getId()));
    if (getUser() == null)
    {
    }
    else
    {
      String encryptedOldPassword = encryptPassword(oldPassword);
      if (getUser().getPassword().equals(encryptedOldPassword))
      {
        // Old Password is correct.
        if (!newPassword.equals(confirmPassword))
        {
          errors.addGlobalError(new SimpleError("New and Confirm passwords don't match."));  
        }
      }
      else
      {
        errors.addGlobalError(new SimpleError("Old Password is incorrect."));
      }
    }
  }
  
  /**
   * The new password should already be validated for acceptable length and that it matches the confirm password.
   * 
   * @param errors
   */
  @ValidationMethod(on="save", priority=99)
  public void validatePasswordContainsSpecialCharacter(ValidationErrors errors)
  {
    Pattern pattern = Pattern.compile(ConverterConstants.PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(newPassword);
    if (matcher.matches())
    {
      getUser().setPassword(encryptPassword(newPassword));
    }
    else
    {
      errors.addGlobalError(new SimpleError("The password must be " + ConverterConstants.MIN_PASSWORD_LENGTH + " to " + ConverterConstants.MAX_PASSWORD_LENGTH + " characters in length with at least one digit, one upper case letter, one lower case letter and one special symbol: " + ConverterConstants.PASSWORD_SPECIAL_CHARACTERS));  
    }
  }
}
