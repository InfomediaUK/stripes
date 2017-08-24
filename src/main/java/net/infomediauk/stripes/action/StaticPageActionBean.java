package net.infomediauk.stripes.action;

import java.util.List;

import stripesbook.action.BaseActionBean;
import net.infomediauk.model.Job;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class StaticPageActionBean extends BaseActionBean
{
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
    return new ForwardResolution("/WEB-INF/jsp/" + getView().toLowerCase() + "/staticPage.jsp");
  }
}
