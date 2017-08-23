package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlJobDao;
import net.infomediauk.model.Job;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class JobActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/job.jsp";
  @ValidateNestedProperties({@Validate(field="name", required=true)})
  private Job job;
  private Integer id;

  public Job getJob()
  {
    return job;
  }

  public void setJob(Job job)
  {
    this.job = job;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }


  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    job = XmlJobDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlJobDao.getInstance().update(job);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    Job deletedJob = XmlJobDao.getInstance().select(job.getId());
    if (XmlJobDao.getInstance().delete(job.getId()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedJob.getName()));
      return new RedirectResolution(JobListActionBean.class);
    }
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlJobDao.getInstance().update(job);
    return new RedirectResolution(JobListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(JobListActionBean.class);
  }

}
