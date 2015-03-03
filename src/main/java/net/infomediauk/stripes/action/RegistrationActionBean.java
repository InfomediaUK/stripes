package net.infomediauk.stripes.action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlDomicileDao;
import net.infomediauk.dao.impl.XmlLengthOfStayDao;
import net.infomediauk.dao.impl.XmlProspectDao;
import net.infomediauk.dao.impl.XmlTitleDao;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Discipline;
import net.infomediauk.model.Domicile;
import net.infomediauk.model.LengthOfStay;
import net.infomediauk.model.ProspectShort;
import net.infomediauk.model.Title;
import net.infomediauk.model.Visa;
import net.infomediauk.utils.DateManager;
import net.infomediauk.utils.DayNumber;
import net.infomediauk.utils.Month;
import net.infomediauk.utils.Year;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class RegistrationActionBean extends BaseActionBean
{
  @ValidateNestedProperties({
    @Validate(field="firstName", required=true),
    @Validate(field="lastName", required=true),
    @Validate(field="contactTelephone", required=true),
    @Validate(field="email", required=true, converter=EmailTypeConverter.class),
    @Validate(field="profession", required=true)
  })
  private Prospect prospect;
  private ProspectShort prospectShort;
  private FileBean fileBean;
//  private List<DayNumber> dayNumberList;
//  private List<Month> monthList;
//  private List<Year> yearList;
  private List<Domicile> domicileList;
  private List<LengthOfStay> lengthOfStayList;
  private List<Discipline> disciplineList;
  private List<Visa> visaList;
  private List<Title> titleList;
  private Integer domicileId;
  private Integer disciplineId;
  private Integer lengthOfStayId;
  private Integer visaId;
  private Integer availableDayNumber;
  private Integer availableMonth;
  private Integer availableYear;
  private String title;
  private DateManager dateManager;
  
  public RegistrationActionBean()
  {
    super();
    dateManager = new DateManager();
//    buildDayNUmberList();
//    buildMonthList();
//    buildYearList();
    domicileList     = XmlDomicileDao.getInstance().selectAll();
    disciplineList   = XmlDisciplineDao.getInstance().selectAll();
    lengthOfStayList = XmlLengthOfStayDao.getInstance().selectAll();
    visaList         = XmlVisaDao.getInstance().selectAll();
    titleList        = XmlTitleDao.getInstance().selectAll();
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

  public void setDomicileList(List<Domicile> domicileList)
  {
    this.domicileList = domicileList;
  }

  public List<LengthOfStay> getLengthOfStayList()
  {
    return lengthOfStayList;
  }

  public void setLengthOfStayList(List<LengthOfStay> lengthOfStayList)
  {
    this.lengthOfStayList = lengthOfStayList;
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

  public List<Title> getTitleList()
  {
    return titleList;
  }

  public void setTitleList(List<Title> titleList)
  {
    this.titleList = titleList;
  }

  public Integer getDomicileId()
  {
    return domicileId;
  }

  public void setDomicileId(Integer domicileId)
  {
    this.domicileId = domicileId;
  }

  public Integer getLengthOfStayId()
  {
    return lengthOfStayId;
  }

  public void setLengthOfStayId(Integer lengthOfStayId)
  {
    this.lengthOfStayId = lengthOfStayId;
  }

  public Integer getDisciplineId()
  {
    return disciplineId;
  }

  public void setDisciplineId(Integer disciplineId)
  {
    this.disciplineId = disciplineId;
  }

  public Integer getVisaId()
  {
    return visaId;
  }

  public void setVisaId(Integer visaId)
  {
    this.visaId = visaId;
  }

  public List<DayNumber> getDayNumberList()
  {
    return dateManager.getDayNumberList();
  }

//  public void setDayNumberList(List<DayNumber> dayNumberList)
//  {
//    this.dayNumberList = dayNumberList;
//  }
//
  public List<Month> getMonthList()
  {
    return dateManager.getMonthList();
  }

//  public void setMonthList(List<Month> monthList)
//  {
//    this.monthList = monthList;
//  }
//
  public List<Year> getYearList()
  {
    return dateManager.getYearList();
  }

//  public void setYearList(List<Year> yearList)
//  {
//    this.yearList = yearList;
//  }
//
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

  public String getTitle()
  {
    return title;
  }

  @Validate(required=true)
  public void setTitle(String title)
  {
    this.title = title;
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
    }
    // Prime initial with this year that will be always be id 1.
    availableYear = 1;
    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
  }

  public Resolution save()
  {
    // Set values from lists.
    prospect.setTitle(title);
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
      prospect.setDocumentFileName("NOT SUPPLIED");
    }
    else
    {
      String fileName = fileBean.getFileName();
      System.out.println(fileName);
      System.out.println(fileBean.getContentType());
      System.out.println(fileBean.getSize());
      String newFileName = prospect.getEmail() + fileName.substring(fileName.lastIndexOf("."));
      String newFullFileName = getProspectFilesFolder() + "/" + newFileName; 
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

    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
//    return new RedirectResolution(RegisterActionBean.class);
  }

//  private void buildDayNUmberList()
//  {
//    dayNumberList = new ArrayList<DayNumber>(32);
//    dayNumberList.add(new DayNumber(0, "Day"));
//    for (int i = 1; i < 31; i++)
//    {
//      dayNumberList.add(new DayNumber(i, new Integer(i).toString()));
//    }
//  }
//  
//  private void buildMonthList()
//  {
//    DateFormatSymbols dfs = new DateFormatSymbols(Locale.ENGLISH);
//    String[] monthNames = dfs.getMonths();
//    monthList = new ArrayList<Month>(13);
//    monthList.add(new Month(0, "Month"));
//    for (int i = 0; i < 12; i++) 
//    {
//      monthList.add(new Month(i + 1, monthNames[i]));
//    }
//  }
//
//  private void buildYearList()
//  {
//    yearList = new ArrayList<Year>(5);
//    yearList.add(new Year(0, "Year"));
//    Calendar calendar = Calendar.getInstance();
//    int year = 0;
//    for (int i = 1; i < 5; i++)
//    {
//      year = calendar.get(Calendar.YEAR);
//      yearList.add(new Year(Integer.valueOf(i), String.valueOf(year)));
//      calendar.add(Calendar.YEAR, 1);
//    }
//  }
  
//  @ValidationMethod(on={"save"})
//  public void repopulateList()
//  {
//    disciplineList = XmlDisciplineDao.getInstance().selectAll();
//    visaList = XmlVisaDao.getInstance().selectAll();
//  }
}
