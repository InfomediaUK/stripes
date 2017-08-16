package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlUserDao;
import net.infomediauk.model.User;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class UserListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/userList.jsp";
  private List<User> userList;

  public List<User> getUserList()
  {
    return userList;
  }

  public void setUserList(List<User> userList)
  {
    this.userList = userList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    userList = XmlUserDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution delete()
  {
    XmlUserDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlUserDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlUserDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }
  
}
