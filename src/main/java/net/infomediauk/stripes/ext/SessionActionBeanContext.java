package net.infomediauk.stripes.ext;

import net.infomediauk.model.User;
import net.sourceforge.stripes.action.ActionBeanContext;

/*
 * Handles session variables.
 * 
 * Added package to Extension.Packages in StripesFilter in web.xml.
 */
public class SessionActionBeanContext extends ActionBeanContext
{
  public User getUser()
  {
    return (User)getRequest().getSession().getAttribute("USER_LOGGED_IN");
  }
  
  public void setUser(Object value)
  {
    getRequest().getSession().setAttribute("USER_LOGGED_IN", value);
  }
}
