package net.infomediauk.stripes.action;

import java.util.List;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Discipline;
import net.infomediauk.model.Prospect;
import net.infomediauk.model.ProspectShort;
import net.infomediauk.model.Visa;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class RegisterActionBean extends BaseActionBean
{
  private List<Discipline> disciplineList;
  private List<Visa> visaList;
  private ProspectShort prospectShort;
  private Prospect prospect;

  public List<Discipline> getDisciplineList()
  {
    return disciplineList;
  }

  public void setDisciplineList(List<Discipline> disciplineList)
  {
    this.disciplineList = disciplineList;
  }

  public List<Visa> getVisaList()
  {
    return visaList;
  }

  public void setVisaList(List<Visa> visaList)
  {
    this.visaList = visaList;
  }

  public ProspectShort getProspectShort()
  {
    return prospectShort;
  }

  public void setProspectShort(ProspectShort prospectShort)
  {
    this.prospectShort = prospectShort;
  }

  public Prospect getProspect()
  {
    return prospect;
  }

  public void setProspect(Prospect prospect)
  {
    this.prospect = prospect;
  }

  public Resolution register() throws Exception
  {
    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
  }

  @DefaultHandler
  public Resolution start() throws Exception
  {
    disciplineList = XmlDisciplineDao.getInstance().selectAll();
    visaList = XmlVisaDao.getInstance().selectAll();
    if (prospectShort != null)
    {
      // The Prospect Short form needs to be cleared now. 
      // See web.xml net.sourceforge.stripes.tag.BeanFirstPopulationStrategy
      prospect = new Prospect(prospectShort);
      prospectShort.clear();
    }
    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
  }

}
