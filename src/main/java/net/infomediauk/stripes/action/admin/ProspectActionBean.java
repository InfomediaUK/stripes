package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class ProspectActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/prospect.jsp";
  private Prospect discipline;
  private String prospectFileName;

  public String getProspectFileName()
  {
    return prospectFileName;
  }

  public Prospect getProspect()
  {
    return discipline;
  }

  public void setProspect(Prospect discipline)
  {
    this.discipline = discipline;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    return new ForwardResolution(FORM);
  }
  
  @DontValidate
  public Resolution delete()
  {
//    Prospect deletedProspect = XmlProspectDao.getInstance().select(discipline.getId());
//    XmlProspectDao.getInstance().delete(discipline.getId());
//    getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedProspect.getName()));
    return new RedirectResolution(ProspectListActionBean.class);
  }
  
  public Resolution save()
  {
//    XmlProspectDao.getInstance().update(discipline);
    return new RedirectResolution(ProspectListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(ProspectListActionBean.class);
  }

}
