package net.infomediauk.stripes.action;

import javax.servlet.http.HttpSession;

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
//    HttpSession session = getContext().getRequest().getSession();
//    if (session.getAttribute("view") == null)
//    {
//      UAgentInfo uAgentInfo = new UAgentInfo(getContext().getRequest().getHeader("User-Agent"), getContext().getRequest().getHeader("Accept"));
//      if (uAgentInfo.detectMobileLong())
//      {
//        session.setAttribute("view", "MOBILE");
//      }
//      else
//      {
//        session.setAttribute("view", "SITE");
//      }
//    }
    if (getView().equals("MOBILE"))
    {
      return new ForwardResolution("/WEB-INF/jsp/mobile/staticPage.jsp");
    }
    return new ForwardResolution("/WEB-INF/jsp/site/staticPage.jsp");
  }
}
