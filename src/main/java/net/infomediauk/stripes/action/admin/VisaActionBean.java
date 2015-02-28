package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Visa;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class VisaActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/visa.jsp";
  @ValidateNestedProperties({@Validate(field="name", required=true)})
  private Visa visa;
  private Integer id;

  public Visa getVisa()
  {
    return visa;
  }

  public void setVisa(Visa visa)
  {
    this.visa = visa;
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
    visa = XmlVisaDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlVisaDao.getInstance().update(visa);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    XmlVisaDao.getInstance().delete(visa.getId());
    return new RedirectResolution(VisaListActionBean.class);
  }
  
  public Resolution save()
  {
    XmlVisaDao.getInstance().update(visa);
    return new RedirectResolution(VisaListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(VisaListActionBean.class);
  }

}
