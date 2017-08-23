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
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;

public class DisciplineActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/discipline.jsp";
  @ValidateNestedProperties({
    @Validate(field="id", required=true),
    @Validate(field="name", required=true)
  })
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
    if (XmlDisciplineDao.getInstance().delete(discipline.getId()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedDiscipline.getName()));
      return new RedirectResolution(DisciplineListActionBean.class);
    }
    ValidationErrors validationErrors = getContext().getValidationErrors();
    validationErrors.add("discipline.name", new SimpleError("Is in existing Prospect. Cannot delete it"));
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlDisciplineDao.getInstance().backupDatabase();
    XmlDisciplineDao.getInstance().update(discipline);
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", discipline.getName()));
    return new RedirectResolution(DisciplineListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(DisciplineListActionBean.class);
  }

}
