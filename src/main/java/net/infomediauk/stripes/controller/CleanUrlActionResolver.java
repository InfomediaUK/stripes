package net.infomediauk.stripes.controller;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.NameBasedActionResolver;

public class CleanUrlActionResolver extends NameBasedActionResolver
{
  @Override
  protected String getBindingSuffix()
  {
    return ".action";
  }
  
  @Override
  protected ActionBean handleActionBeanNotFound(ActionBeanContext context, String urlBinding)
  {
    ActionBean actionBean = super.handleActionBeanNotFound(context, urlBinding);
    Configuration configuration = getConfiguration();
    System.out.println(configuration.toString());
    return actionBean;
  }
}
