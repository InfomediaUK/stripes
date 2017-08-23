package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlDomicileDao;
import net.infomediauk.model.Domicile;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class DomicileListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/domicileList.jsp";
  private List<Domicile> domicileList;

  public List<Domicile> getDomicileList()
  {
    return domicileList;
  }

  public void setDomicileList(List<Domicile> domicileList)
  {
    this.domicileList = domicileList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    domicileList = XmlDomicileDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution revert()
  {
    XmlDomicileDao.getInstance().revertDatabase();
    domicileList = XmlDomicileDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }
  
  public Resolution delete()
  {
    XmlDomicileDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlDomicileDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlDomicileDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }
  
}
