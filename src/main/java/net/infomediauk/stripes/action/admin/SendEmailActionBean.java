package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class SendEmailActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/sendemail.jsp";

  @DefaultHandler
  public Resolution view() throws Exception
  {
    return new ForwardResolution(FORM);
  }
  
}
