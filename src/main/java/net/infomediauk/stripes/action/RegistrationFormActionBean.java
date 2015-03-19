package net.infomediauk.stripes.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlPassportDao;
import net.infomediauk.dao.impl.XmlLengthOfStayDao;
import net.infomediauk.dao.impl.XmlProspectDao;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Discipline;
import net.infomediauk.model.Passport;
import net.infomediauk.model.LengthOfStay;
import net.infomediauk.model.ProspectShort;
import net.infomediauk.model.Visa;
import net.infomediauk.xml.jaxb.model.Gender;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.infomediauk.xml.jaxb.model.ProspectFile;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
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
    @Validate(field="profession", required=true),
    @Validate(field="availableForWork", required=true)
  })
  private Prospect prospect;
  private ProspectShort prospectShort;
  private Gender gender;
  private FileBean fileBean;
  private List<Passport> passportList;
  private List<LengthOfStay> lengthOfStayList;
  private List<Discipline> disciplineList;
  private List<Visa> visaList;
  private Integer passportId;
  private Integer disciplineId;
  private Integer lengthOfStayId;
  private Integer visaId;
  private String email;
  
  public RegistrationFormActionBean()
  {
    super();
    passportList     = XmlPassportDao.getInstance().selectAll();
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
  public void setGender(Gender gender)
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

  public List<Passport> getPassportList()
  {
    return passportList;
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

  public Integer getPassportId()
  {
    return passportId;
  }

  @Validate(required=true)
  public void setPassportId(Integer passportId)
  {
    this.passportId = passportId;
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

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
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
    return new ForwardResolution("/WEB-INF/jsp/" + getView().toLowerCase() + "/registration.jsp");
  }

  @DontValidate
  public Resolution thanks()
  {
    ProspectFile prospectFile = XmlProspectDao.getInstance().select(email + ".xml");
    prospect = prospectFile.getProspect();
    return new ForwardResolution("/WEB-INF/jsp/" + getView().toLowerCase() + "/registrationThanks.jsp");
  }
  
  public Resolution save()
  {
    // Set values from lists.
    prospect.setGender(gender);
    prospect.setPassportId(passportId);
    prospect.setDisciplineId(disciplineId);
    prospect.setVisaId(visaId);
    prospect.setLengthOfStayId(lengthOfStayId);
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

    return new RedirectResolution(RegistrationFormActionBean.class, "thanks").addParameter("email", prospect.getEmail());
  }

}
