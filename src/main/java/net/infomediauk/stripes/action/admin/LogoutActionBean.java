package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.stripes.ext.SessionActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/admin/logout.htm")
public class LogoutActionBean extends BaseActionBean
{
  public Resolution logout() throws Exception
  {
    System.out.println("In Logout...");
    SessionActionBeanContext sessionActionBeanContext = getContext();
    sessionActionBeanContext.logout();
    return new RedirectResolution(LoginActionBean.class);
  }
}
