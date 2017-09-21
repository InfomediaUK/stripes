package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlPassportDao;
import net.infomediauk.model.Passport;
import net.infomediauk.stripes.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

public class PassportListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/passportList.jsp";
  private List<Passport> passportList;

  public List<Passport> getPassportList()
  {
    return passportList;
  }

  public void setPassportList(List<Passport> passportList)
  {
    this.passportList = passportList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("Passports");
    passportList = XmlPassportDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution revert()
  {
    XmlPassportDao.getInstance().revertDatabase();
    passportList = XmlPassportDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }
  
  public Resolution delete()
  {
    XmlPassportDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlPassportDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlPassportDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }

  public Resolution refreshFromMMJ()
  {
    XmlPassportDao.getInstance().refreshFromWeb();
    return new RedirectResolution(PassportListActionBean.class);
  }
  
}
