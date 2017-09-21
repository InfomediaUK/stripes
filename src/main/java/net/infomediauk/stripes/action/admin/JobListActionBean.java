package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.util.List;

import net.infomediauk.dao.impl.XmlJobDao;
import net.infomediauk.model.Job;
import net.infomediauk.stripes.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

public class JobListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/jobList.jsp";
  private List<Job> jobList;

  public List<Job> getJobList()
  {
    return jobList;
  }

  public void setJobList(List<Job> jobList)
  {
    this.jobList = jobList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("Jobs");
    jobList = XmlJobDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution revert()
  {
    XmlJobDao.getInstance().revertDatabase();
    jobList = XmlJobDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }
  
  public Resolution delete()
  {
    XmlJobDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlJobDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlJobDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }
  
}
