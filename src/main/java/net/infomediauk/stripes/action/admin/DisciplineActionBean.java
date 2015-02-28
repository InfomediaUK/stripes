package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.model.Discipline;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class DisciplineActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/discipline.jsp";
  @ValidateNestedProperties({@Validate(field="name", required=true)})
  private Discipline discipline;
  private Integer id;

  public Discipline getDiscipline()
  {
    return discipline;
  }

  public void setDiscipline(Discipline discipline)
  {
    this.discipline = discipline;
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
    discipline = XmlDisciplineDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlDisciplineDao.getInstance().update(discipline);
    return new ForwardResolution(FORM);
  }

  @DontValidate
  public Resolution delete()
  {
    Discipline deletedDiscipline = XmlDisciplineDao.getInstance().select(discipline.getId());
    XmlDisciplineDao.getInstance().delete(discipline.getId());
    getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedDiscipline.getName()));
    return new RedirectResolution(DisciplineListActionBean.class);
  }
  
  public Resolution save()
  {
    XmlDisciplineDao.getInstance().update(discipline);
    return new RedirectResolution(DisciplineListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(DisciplineListActionBean.class);
  }

}
