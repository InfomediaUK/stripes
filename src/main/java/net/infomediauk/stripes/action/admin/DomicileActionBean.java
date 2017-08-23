package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlDomicileDao;
import net.infomediauk.model.Domicile;
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

public class DomicileActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/domicile.jsp";
  @ValidateNestedProperties({@Validate(field="name", required=true)})
  private Domicile domicile;
  private Integer id;

  public Domicile getDomicile()
  {
    return domicile;
  }

  public void setDomicile(Domicile domicile)
  {
    this.domicile = domicile;
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
    domicile = XmlDomicileDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlDomicileDao.getInstance().update(domicile);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    Domicile deletedDomicile = XmlDomicileDao.getInstance().select(domicile.getId());
    if (XmlDomicileDao.getInstance().delete(domicile.getId()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedDomicile.getName()));
      return new RedirectResolution(DomicileListActionBean.class);
    }
    ValidationErrors validationErrors = getContext().getValidationErrors();
    validationErrors.add("domicile.name", new SimpleError("Is in existing Prospect. Cannot delete it"));
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlDomicileDao.getInstance().backupDatabase();
    XmlDomicileDao.getInstance().update(domicile);
    return new RedirectResolution(DomicileListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(DomicileListActionBean.class);
  }

}
