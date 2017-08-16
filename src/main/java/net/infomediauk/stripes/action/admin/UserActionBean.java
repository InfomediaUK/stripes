package net.infomediauk.stripes.action.admin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import stripesbook.action.BaseActionBean;
import net.infomediauk.model.User;
import net.sourceforge.stripes.util.Base64;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class UserActionBean extends BaseActionBean
{
  @ValidateNestedProperties({
    @Validate(field="name", required=true),
    @Validate(field="email", required=true, converter=EmailTypeConverter.class)
  })
  private User user;

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  protected String encryptPassword(String password)
  {
    try
    {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] bytes = md.digest(password.getBytes());
      return Base64.encodeBytes(bytes);
    }
    catch (NoSuchAlgorithmException exc)
    {
      throw new IllegalArgumentException(exc);
    }
  }
}
