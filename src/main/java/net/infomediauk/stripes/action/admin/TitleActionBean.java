package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlRoleDao;
import net.infomediauk.dao.impl.XmlTitleDao;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Title;
import net.infomediauk.model.Visa;
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

public class TitleActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/title.jsp";
  @ValidateNestedProperties({@Validate(field="name", required=true)})
  private Title title;
  private Integer id;

  public Title getTitle()
  {
    return title;
  }

  public void setTitle(Title title)
  {
    this.title = title;
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
    title = XmlTitleDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlTitleDao.getInstance().update(title);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    Title deletedTitle = XmlTitleDao.getInstance().select(title.getId());
    if (XmlTitleDao.getInstance().delete(title.getId(), title.getName()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedTitle.getName()));
      return new RedirectResolution(TitleListActionBean.class);
    }
    ValidationErrors validationErrors = getContext().getValidationErrors();
    validationErrors.add("title.name", new SimpleError("Is in existing Prospect. Cannot delete it"));
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlTitleDao.getInstance().backupDatabase();
    XmlTitleDao.getInstance().update(title);
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", title.getName()));
    return new RedirectResolution(TitleListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(TitleListActionBean.class);
  }

}
