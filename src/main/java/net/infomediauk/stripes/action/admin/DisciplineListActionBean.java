package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlTitleDao;
import net.infomediauk.model.Discipline;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class DisciplineListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/disciplineList.jsp";
  private List<Discipline> disciplineList;

  public List<Discipline> getDisciplineList()
  {
    return disciplineList;
  }

  public void setDisciplineList(List<Discipline> disciplineList)
  {
    this.disciplineList = disciplineList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    disciplineList = XmlDisciplineDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution delete()
  {
    XmlDisciplineDao.getInstance().deleteData();
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
    FileInputStream inputStream = XmlDisciplineDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }

  public Resolution refreshFromMMJ()
  {
    XmlDisciplineDao.getInstance().refreshFromWeb();
    return new RedirectResolution(DisciplineListActionBean.class);
  }
  
  
}
