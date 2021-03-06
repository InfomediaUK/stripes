package net.infomediauk.stripes.action.admin;

import java.util.List;

import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlJobDao;
import net.infomediauk.model.Discipline;
import net.infomediauk.model.Job;
import net.infomediauk.stripes.action.BaseActionBean;
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
  // List
  private List<Discipline> disciplineList;
  // The value returned from the List.
  private Integer disciplineId;

  public JobActionBean()
  {
    super();
    disciplineList = XmlDisciplineDao.getInstance().selectAll();
  }

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

  public List<Discipline> getDisciplineList()
  {
    return disciplineList;
  }

  public Integer getDisciplineId()
  {
    return disciplineId;
  }

  public void setDisciplineId(Integer disciplineId)
  {
    this.disciplineId = disciplineId;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("Job");
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
    XmlJobDao.getInstance().backupDatabase();
    job.setDisciplineId(disciplineId);
    XmlJobDao.getInstance().update(job);
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", job.getName()));
    return new RedirectResolution(JobListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(JobListActionBean.class);
  }

}
