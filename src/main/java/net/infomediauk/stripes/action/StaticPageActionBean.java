package net.infomediauk.stripes.action;

import com.handinteractive.mobile.UAgentInfo;

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
    UAgentInfo uAgentInfo = new UAgentInfo(getContext().getRequest().getHeader("User-Agent"), getContext().getRequest().getHeader("Accept"));
    if (uAgentInfo.detectMobileLong())
    {
      return new ForwardResolution("/WEB-INF/jsp/mobile/staticPage.jsp");
    }
    return new ForwardResolution("/WEB-INF/jsp/www/staticPage.jsp");
  }
}
