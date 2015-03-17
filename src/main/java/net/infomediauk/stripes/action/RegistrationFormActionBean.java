package net.infomediauk.stripes.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlDomicileDao;
import net.infomediauk.dao.impl.XmlLengthOfStayDao;
import net.infomediauk.dao.impl.XmlProspectDao;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Discipline;
import net.infomediauk.model.Domicile;
import net.infomediauk.model.LengthOfStay;
import net.infomediauk.model.ProspectShort;
import net.infomediauk.model.Visa;
import net.infomediauk.utils.DateManager;
import net.infomediauk.utils.DayNumber;
import net.infomediauk.utils.Month;
import net.infomediauk.utils.Year;
import net.infomediauk.xml.jaxb.model.Gender;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class RegistrationFormActionBean extends BaseActionBean
{
  @ValidateNestedProperties({
    @Validate(field="firstName", required=true),
    @Validate(field="lastName", required=true),
    @Validate(field="mobileTelephone", required=true),
    @Validate(field="email", required=true, converter=EmailTypeConverter.class),
    @Validate(field="profession", required=true)
  })
  private Prospect prospect;
  private ProspectShort prospectShort;
  private Gender gender;
  private FileBean fileBean;
  private List<Domicile> domicileList;
  private List<LengthOfStay> lengthOfStayList;
  private List<Discipline> disciplineList;
  private List<Visa> visaList;
  private Integer domicileId;
  private Integer disciplineId;
  private Integer lengthOfStayId;
  private Integer visaId;
  private Integer availableDayNumber;
  private Integer availableMonth;
  private Integer availableYear;
  private DateManager dateManager;
  
  public RegistrationFormActionBean()
  {
    super();
    dateManager      = new DateManager();
    domicileList     = XmlDomicileDao.getInstance().selectAll();
    disciplineList   = XmlDisciplineDao.getInstance().selectAll();
    lengthOfStayList = XmlLengthOfStayDao.getInstance().selectAll();
    visaList         = XmlVisaDao.getInstance().selectAll();
  }

  public Prospect getProspect()
  {
    return prospect;
  }

  public void setProspect(Prospect prospect)
  {
    this.prospect = prospect;
  }

  public ProspectShort getProspectShort()
  {
    return prospectShort;
  }

  public void setProspectShort(ProspectShort prospectShort)
  {
    this.prospectShort = prospectShort;
  }

  public Gender getGender()
  {
    return gender;
  }

  @Validate(required=true)
  public void setGender(Gender gender) // , converter=EnumeratedTypeConverter.class
  {
    this.gender = gender;
  }

  public FileBean getFileBean()
  {
    return fileBean;
  }

  public void setFileBean(FileBean fileBean)
  {
    this.fileBean = fileBean;
  }

  public List<Domicile> getDomicileList()
  {
    return domicileList;
  }

  public List<LengthOfStay> getLengthOfStayList()
  {
    return lengthOfStayList;
  }

  public List<Discipline> getDisciplineList()
  {
    return disciplineList;
  }

  public List<Visa> getVisaList()
  {
    return visaList;
  }

  public Integer getDomicileId()
  {
    return domicileId;
  }

  @Validate(required=true)
  public void setDomicileId(Integer domicileId)
  {
    this.domicileId = domicileId;
  }

  public Integer getLengthOfStayId()
  {
    return lengthOfStayId;
  }

  @Validate(required=true)
  public void setLengthOfStayId(Integer lengthOfStayId)
  {
    this.lengthOfStayId = lengthOfStayId;
  }

  public Integer getDisciplineId()
  {
    return disciplineId;
  }

  @Validate(required=true)
  public void setDisciplineId(Integer disciplineId)
  {
    this.disciplineId = disciplineId;
  }

  public Integer getVisaId()
  {
    return visaId;
  }

  @Validate(required=true)
  public void setVisaId(Integer visaId)
  {
    this.visaId = visaId;
  }

  public List<DayNumber> getDayNumberList()
  {
    return dateManager.getDayNumberList();
  }

  public List<Month> getMonthList()
  {
    return dateManager.getMonthList();
  }

  public List<Year> getYearList()
  {
    return dateManager.getYearList();
  }

  public Integer getAvailableDayNumber()
  {
    return availableDayNumber;
  }

  public void setAvailableDayNumber(Integer day)
  {
    this.availableDayNumber = day;
  }

  public Integer getAvailableMonth()
  {
    return availableMonth;
  }

  public void setAvailableMonth(Integer month)
  {
    this.availableMonth = month;
  }

  public Integer getAvailableYear()
  {
    return availableYear;
  }

  public void setAvailableYear(Integer availableYear)
  {
    this.availableYear = availableYear;
  }

  public Resolution register() throws Exception
  {
    return new ForwardResolution("/WEB-INF/jsp/" + getView().toLowerCase() + "/registration.jsp");
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
    }
    Locale locale    = getContext().getLocale();
    System.out.println(locale);
    // Prime initial with this year that will be always be id 1.
    availableYear = 1;
    return new ForwardResolution("/WEB-INF/jsp/" + getView().toLowerCase() + "/registration.jsp");
  }

  public Resolution save()
  {
    // Set values from lists.
    prospect.setGender(gender);
    prospect.setDomicileId(domicileId);
    prospect.setDisciplineId(disciplineId);
    prospect.setVisaId(visaId);
    prospect.setLengthOfStayId(lengthOfStayId);
    StringBuffer availableForWork = new StringBuffer();
    if (availableDayNumber > 0)
    {
      availableForWork.append(availableDayNumber.toString());
      availableForWork.append(" ");
    }
    if (availableMonth > 0)
    {
      availableForWork.append(dateManager.getMonthName(availableMonth).toString());
      availableForWork.append(" ");
    }
    if (!availableYear.equals("Year"))
    {
      availableForWork.append(dateManager.getYearName(availableYear).toString());
    }
    System.out.println(availableDayNumber);
    System.out.println(availableMonth);
    System.out.println(availableYear);
    availableForWork.trimToSize();
    prospect.setAvailableForWork(availableForWork.toString());
    System.out.println(prospect.getAvailableForWork());
    if (fileBean == null)
    {
      prospect.setDocumentFileName("");
    }
    else
    {
      String fileName = fileBean.getFileName();
      System.out.println(fileName);
      System.out.println(fileBean.getContentType());
      System.out.println(fileBean.getSize());
      String newFileName = prospect.getEmail() + fileName.substring(fileName.lastIndexOf("."));
      String newFullFileName = XmlProspectDao.getInstance().getProspectFilesFolder() + "/" + newFileName; 
      try
      {
        fileBean.save(new File(newFullFileName));
        prospect.setDocumentFileName(newFileName);
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    XmlProspectDao.getInstance().saveProspect(prospect);

    return new ForwardResolution("/WEB-INF/jsp/" + getView().toLowerCase() + "/registration.jsp");
  }

}
