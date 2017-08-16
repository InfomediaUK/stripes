package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlRoleDao;
import net.infomediauk.model.Role;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class RoleActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/role.jsp";
  @ValidateNestedProperties({@Validate(field="name", required=true)})
  private Role role;
  private Integer id;

  public Role getRole()
  {
    return role;
  }

  public void setRole(Role role)
  {
    this.role = role;
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
    role = XmlRoleDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlRoleDao.getInstance().update(role);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    Role deletedRole = XmlRoleDao.getInstance().select(role.getId());
    if (XmlRoleDao.getInstance().delete(role.getId(), role.getName()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedRole.getName()));
      return new RedirectResolution(RoleListActionBean.class);
    }
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlRoleDao.getInstance().update(role);
    return new RedirectResolution(RoleListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(RoleListActionBean.class);
  }

}
