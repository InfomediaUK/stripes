package net.infomediauk.stripes.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import net.infomediauk.dao.impl.XmlTitleDao;
import net.infomediauk.model.Title;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class TitleListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/titleList.jsp";
  private List<Title> titleList;

  public List<Title> getTitleList()
  {
    return titleList;
  }

  public void setTitleList(List<Title> titleList)
  {
    this.titleList = titleList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    titleList = XmlTitleDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution delete()
  {
    String fileName = XmlTitleDao.getInstance().getFileName();
    File file = XmlTitleDao.getInstance().getFile();
    file.delete();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlTitleDao.getInstance().getFileName();
    File file = XmlTitleDao.getInstance().getFile();
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
