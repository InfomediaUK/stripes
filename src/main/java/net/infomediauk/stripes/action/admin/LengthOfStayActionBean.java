package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlLengthOfStayDao;
import net.infomediauk.model.LengthOfStay;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class LengthOfStayActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/lengthOfStay.jsp";
  @ValidateNestedProperties({@Validate(field="name", required=true)})
  private LengthOfStay lengthOfStay;
  private Integer id;

  public LengthOfStay getLengthOfStay()
  {
    return lengthOfStay;
  }

  public void setLengthOfStay(LengthOfStay lengthOfStay)
  {
    this.lengthOfStay = lengthOfStay;
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
    lengthOfStay = XmlLengthOfStayDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlLengthOfStayDao.getInstance().update(lengthOfStay);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    XmlLengthOfStayDao.getInstance().delete(lengthOfStay.getId());
    return new RedirectResolution(LengthOfStayListActionBean.class);
  }
  
  public Resolution save()
  {
    XmlLengthOfStayDao.getInstance().update(lengthOfStay);
    return new RedirectResolution(LengthOfStayListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(LengthOfStayListActionBean.class);
  }

}
