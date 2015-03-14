package net.infomediauk.stripes.action;

import stripesbook.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class StaticPageActionBean extends BaseActionBean
{
  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
//    if (getView().equals("MOBILE"))
//    {
//      return new ForwardResolution("/WEB-INF/jsp/mobile/staticPage.jsp");
//    }
    return new ForwardResolution("/WEB-INF/jsp/" + getView().toLowerCase() + "/staticPage.jsp");
  }
}
