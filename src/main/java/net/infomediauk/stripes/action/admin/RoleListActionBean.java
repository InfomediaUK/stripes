package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlRoleDao;
import net.infomediauk.model.Role;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class RoleListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/roleList.jsp";
  private List<Role> roleList;

  public List<Role> getRoleList()
  {
    return roleList;
  }

  public void setRoleList(List<Role> roleList)
  {
    this.roleList = roleList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    roleList = XmlRoleDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution revert()
  {
    XmlRoleDao.getInstance().revertDatabase();
    roleList = XmlRoleDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }
  
  public Resolution delete()
  {
    XmlRoleDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlRoleDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlRoleDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }
  
}
