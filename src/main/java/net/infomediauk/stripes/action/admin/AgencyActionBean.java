package net.infomediauk.stripes.action.admin;

import net.infomediauk.dao.impl.XmlAgencyDao;
import net.infomediauk.model.Agency;
import net.infomediauk.stripes.action.BaseActionBean;
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

public class AgencyActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/agency.jsp";
  @ValidateNestedProperties({
    @Validate(field="id", required=true),
    @Validate(field="name", required=true)
  })
  private Agency agency;
  private Integer id;

  public Agency getAgency()
  {
    return agency;
  }

  public void setAgency(Agency agency)
  {
    this.agency = agency;
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
    getHtmlPage().setTitle("Agency");
    agency = XmlAgencyDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlAgencyDao.getInstance().update(agency);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    Agency deletedAgency = XmlAgencyDao.getInstance().select(agency.getId());
    if (XmlAgencyDao.getInstance().delete(agency.getId()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedAgency.getName()));
      return new RedirectResolution(AgencyListActionBean.class);
    }
    ValidationErrors validationErrors = getContext().getValidationErrors();
    validationErrors.add("agency.name", new SimpleError("Is in existing Prospect. Cannot delete it"));
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlAgencyDao.getInstance().backupDatabase();
    XmlAgencyDao.getInstance().update(agency);
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", agency.getName()));
    return new RedirectResolution(AgencyListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(AgencyListActionBean.class);
  }

}
