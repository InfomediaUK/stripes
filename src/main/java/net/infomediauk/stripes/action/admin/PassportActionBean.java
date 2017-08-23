package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlLengthOfStayDao;
import net.infomediauk.dao.impl.XmlPassportDao;
import net.infomediauk.model.Passport;
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

public class PassportActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/passport.jsp";
  @ValidateNestedProperties({
    @Validate(field="id", required=true),
    @Validate(field="name", required=true)
  })
  private Passport passport;
  private Integer id;

  public Passport getPassport()
  {
    return passport;
  }

  public void setPassport(Passport passport)
  {
    this.passport = passport;
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
    passport = XmlPassportDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlPassportDao.getInstance().update(passport);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    Passport deletedPassport = XmlPassportDao.getInstance().select(passport.getId());
    if (XmlPassportDao.getInstance().delete(passport.getId()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedPassport.getName()));
      return new RedirectResolution(PassportListActionBean.class);
    }
    ValidationErrors validationErrors = getContext().getValidationErrors();
    validationErrors.add("passport.name", new SimpleError("Is in existing Prospect. Cannot delete it"));
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlPassportDao.getInstance().backupDatabase();
    XmlPassportDao.getInstance().update(passport);
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", passport.getName()));
    return new RedirectResolution(PassportListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(PassportListActionBean.class);
  }

}
