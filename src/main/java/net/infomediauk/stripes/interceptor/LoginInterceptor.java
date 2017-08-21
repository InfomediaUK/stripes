package net.infomediauk.stripes.interceptor;

import stripesbook.action.BaseActionBean;
import net.infomediauk.stripes.action.admin.LoginActionBean;
import net.infomediauk.stripes.ext.SessionActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

/*
 * Intercepts admin actions and redirects to Login page if user is not logged in.
 * 
 * Added to Interceptor.Classes in StripesFilter in web.xml.
 * 
 */
@Intercepts(LifecycleStage.ActionBeanResolution)
public class LoginInterceptor implements Interceptor
{
  public Resolution intercept(ExecutionContext executionContext) throws Exception
  {
    Resolution resolution = executionContext.proceed();
    BaseActionBean actionBean = (BaseActionBean)executionContext.getActionBean();
    String actionBeanClass = actionBean.getClass().toString();
    System.out.println(actionBeanClass);
    if (actionBeanClass.contains("admin"))
    {
      // Admin action requested.
      if (actionBeanClass.contains("LoginActionBean"))
      {
        // Going to Login. Just let it continue...
      }
      else
      {
        // Going to an admin action. Check user is logged in etc.
        SessionActionBeanContext sessionActionBeanContext = (SessionActionBeanContext)executionContext.getActionBeanContext();
        if (sessionActionBeanContext.getUser() == null)
        {
          // User not logged in yet. Redirect to Login page.
          resolution = new RedirectResolution(LoginActionBean.class);
        }
      }
    }
    return resolution;
  }
}
