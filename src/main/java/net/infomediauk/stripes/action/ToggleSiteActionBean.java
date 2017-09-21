package net.infomediauk.stripes.action;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class ToggleSiteActionBean extends BaseActionBean
{

  @DefaultHandler
  public Resolution view() throws Exception
  {
    HttpSession session = getContext().getRequest().getSession();
    if (getView().equals("MOBILE"))
    {
      session.setAttribute("view", "SITE");
    }
    else
    {
      session.setAttribute("view", "MOBILE");
    }
    Class currentActionBeanClass = Class.forName(getCurrentActionBeanClassName());
    return new RedirectResolution(currentActionBeanClass);
  }
}
