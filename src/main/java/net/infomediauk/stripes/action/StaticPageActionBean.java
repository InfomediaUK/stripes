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
    return new ForwardResolution("/WEB-INF/jsp/staticPage.jsp");
  }
}
