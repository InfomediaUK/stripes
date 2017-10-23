package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlIdDocumentDao;
import net.infomediauk.model.IdDocument;
import net.infomediauk.stripes.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

public class IdDocumentListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/idDocumentList.jsp";
  private List<IdDocument> idDocumentList;

  public List<IdDocument> getIdDocumentList()
  {
    return idDocumentList;
  }

  public void setIdDocumentList(List<IdDocument> idDocumentList)
  {
    this.idDocumentList = idDocumentList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("ID Documents");
    idDocumentList = XmlIdDocumentDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution revert()
  {
    XmlIdDocumentDao.getInstance().revertDatabase();
    idDocumentList = XmlIdDocumentDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }
  
  public Resolution delete()
  {
    XmlIdDocumentDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlIdDocumentDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlIdDocumentDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }

  public Resolution refreshFromMMJ()
  {
    XmlIdDocumentDao.getInstance().refreshFromWeb();
    return new RedirectResolution(IdDocumentListActionBean.class);
  }
  
}
