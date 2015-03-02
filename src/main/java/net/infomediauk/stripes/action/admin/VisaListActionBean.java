package net.infomediauk.stripes.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Visa;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class VisaListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/visaList.jsp";
  private List<Visa> visaList;

  public List<Visa> getVisaList()
  {
    return visaList;
  }

  public void setVisaList(List<Visa> visaList)
  {
    this.visaList = visaList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    visaList = XmlVisaDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution delete()
  {
    String fileName = XmlVisaDao.getInstance().getFileName();
    File file = XmlVisaDao.getInstance().getFile();
    file.delete();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlVisaDao.getInstance().getFileName();
    File file = XmlVisaDao.getInstance().getFile();
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
