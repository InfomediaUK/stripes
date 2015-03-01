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
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class RegistrationActionBean extends BaseActionBean
{
  @ValidateNestedProperties({
    @Validate(field="fullName", required=true),
    @Validate(field="contactTelephone", required=true),
    @Validate(field="email", required=true, converter=EmailTypeConverter.class)
  })
  private Prospect prospect;
  private ProspectShort prospectShort;
  private List<Discipline> disciplineList;
  private List<Visa> visaList;
  private String discipline;
  private String visa;
  
  public RegistrationActionBean()
  {
    super();
    disciplineList = XmlDisciplineDao.getInstance().selectAll();
    visaList = XmlVisaDao.getInstance().selectAll();
  }

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

  public Prospect getProspect()
  {
    return prospect;
  }

  public void setProspect(Prospect prospect)
  {
    this.prospect = prospect;
  }

  public String getDiscipline()
  {
    return discipline;
  }

  public ProspectShort getProspectShort()
  {
    return prospectShort;
  }

  public void setProspectShort(ProspectShort prospectShort)
  {
    this.prospectShort = prospectShort;
  }

  public void setDiscipline(String discipline)
  {
    this.discipline = discipline;
  }

  public String getVisa()
  {
    return visa;
  }

  public void setVisa(String visa)
  {
    this.visa = visa;
  }

  public Resolution register() throws Exception
  {
    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    if (prospectShort != null)
    {
      // The Prospect Short form needs to be cleared now. 
      // See web.xml net.sourceforge.stripes.tag.BeanFirstPopulationStrategy
      prospect = new Prospect(prospectShort);
      prospect.setFullName("XXXXXXXXXXXX");;
    }
    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
  }

  public Resolution save()
  {
    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
//    return new RedirectResolution(RegisterActionBean.class);
  }

//  @ValidationMethod(on={"save"})
//  public void repopulateList()
//  {
//    disciplineList = XmlDisciplineDao.getInstance().selectAll();
//    visaList = XmlVisaDao.getInstance().selectAll();
//  }
}
