package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlAgencyDao;
import net.infomediauk.model.Agency;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class AgencyListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/agencyList.jsp";
  private List<Agency> agencyList;

  public List<Agency> getAgencyList()
  {
    return agencyList;
  }

  public void setAgencyList(List<Agency> agencyList)
  {
    this.agencyList = agencyList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    agencyList = XmlAgencyDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution delete()
  {
    XmlAgencyDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlAgencyDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlAgencyDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }

  public Resolution refreshFromMMJ()
  {
    XmlAgencyDao.getInstance().refreshFromWeb();
    return new RedirectResolution(AgencyListActionBean.class);
  }
  
}
