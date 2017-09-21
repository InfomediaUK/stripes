package net.infomediauk.stripes.action.admin;


import net.infomediauk.dao.impl.XmlUserDao;
import net.infomediauk.model.User;
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
import net.sourceforge.stripes.validation.ValidationMethod;

public class UserEditActionBean extends UserActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/userEdit.jsp";
  @ValidateNestedProperties({@Validate(field="name", required=true)})

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("Edit User");
    setUser(XmlUserDao.getInstance().select(getUser().getId()));
    return new ForwardResolution(FORM);
  }
  
  @DontValidate
  public Resolution delete()
  {
    User deletedUser = XmlUserDao.getInstance().select(getUser().getId());
    if (XmlUserDao.getInstance().delete(getUser().getId()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedUser.getName()));
      return new RedirectResolution(UserListActionBean.class);
    }
    ValidationErrors validationErrors = getContext().getValidationErrors();
    validationErrors.add("user.name", new SimpleError("Delete failed."));
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlUserDao.getInstance().backupDatabase();
    XmlUserDao.getInstance().update(getUser());
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", getUser().getName()));
    return new RedirectResolution(UserListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(UserListActionBean.class);
  }

  @ValidationMethod(on="save", priority=0)
  public void validateName(ValidationErrors errors)
  {
    String name = getUser().getName();
    User userToTest = XmlUserDao.getInstance().selectByName(name);
    if (userToTest != null)
    {
      // Check that another User does NOT use the same Name.
      if (!userToTest.getId().equals(getUser().getId()))
      {
        errors.addGlobalError(new SimpleError(name + " is already taken."));
      }
    }
  }
  
}
