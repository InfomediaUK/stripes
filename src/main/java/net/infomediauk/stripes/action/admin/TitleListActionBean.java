package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlTitleDao;
import net.infomediauk.model.Title;
import net.infomediauk.stripes.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

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

  public Resolution revert()
  {
    XmlTitleDao.getInstance().revertDatabase();
    titleList = XmlTitleDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }
  
  public Resolution delete()
  {
    XmlTitleDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlTitleDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlTitleDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }
  
}
