package net.infomediauk.stripes.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/register.htm")
public class RegisterFormActionBean extends BaseActionBean
{
  @DefaultHandler
  public Resolution start() throws Exception
  {
    return new ForwardResolution(RegistrationFormActionBean.class);
  }

}
