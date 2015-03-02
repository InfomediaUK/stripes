package net.infomediauk.stripes.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

  public Resolution delete()
  {
    String fileName = XmlDomicileDao.getInstance().getFileName();
    File file = XmlDomicileDao.getInstance().getFile();
    if (file.delete())
    {
      XmlDomicileDao.getInstance().deleteData();
      System.out.println("Gone!");
    }
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlDomicileDao.getInstance().getFileName();
    File file = XmlDomicileDao.getInstance().getFile();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = null;
    try
    {
      inputStream = new FileInputStream(file);
    }
    catch (FileNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }
  
}
