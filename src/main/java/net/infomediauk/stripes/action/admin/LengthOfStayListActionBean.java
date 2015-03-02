package net.infomediauk.stripes.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import net.infomediauk.dao.impl.XmlLengthOfStayDao;
import net.infomediauk.model.LengthOfStay;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class LengthOfStayListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/lengthOfStayList.jsp";
  private List<LengthOfStay> lengthOfStayList;

  public List<LengthOfStay> getLengthOfStayList()
  {
    return lengthOfStayList;
  }

  public void setLengthOfStayList(List<LengthOfStay> lengthOfStayList)
  {
    this.lengthOfStayList = lengthOfStayList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    lengthOfStayList = XmlLengthOfStayDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution delete()
  {
    String fileName = XmlLengthOfStayDao.getInstance().getFileName();
    File file = XmlLengthOfStayDao.getInstance().getFile();
    file.delete();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlLengthOfStayDao.getInstance().getFileName();
    File file = XmlLengthOfStayDao.getInstance().getFile();
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