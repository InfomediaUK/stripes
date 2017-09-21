package net.infomediauk.stripes.action.admin;

import net.infomediauk.model.User;
import net.infomediauk.stripes.action.BaseActionBean;
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

}
