package net.infomediauk.stripes.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlIdDocumentDao;
import net.infomediauk.dao.impl.XmlLengthOfStayDao;
import net.infomediauk.dao.impl.XmlProspectDao;
import net.infomediauk.dao.impl.XmlSystemSettingsDao;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Discipline;
import net.infomediauk.model.IdDocument;
import net.infomediauk.model.LengthOfStay;
import net.infomediauk.model.Visa;
import net.infomediauk.xml.jaxb.model.Gender;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.infomediauk.xml.jaxb.model.ProspectFile;
import net.infomediauk.xml.jaxb.model.SystemSettings;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.DateTypeConverter;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class RegistrationFormActionBean extends BaseActionBean
{
  @ValidateNestedProperties({
    @Validate(field="firstName", required=true),
    @Validate(field="lastName", required=true),
    @Validate(field="email", required=true, converter=EmailTypeConverter.class),
    @Validate(field="profession", required=true),
    @Validate(field="availableForWork", converter=DateTypeConverter.class) // Note. Do not put required here or save() will not run...
  })
  private Prospect prospect;
  private Gender gender;
  private FileBean fileBean;
  private List<IdDocument> idDocumentList;
  private List<LengthOfStay> lengthOfStayList;
  private List<Discipline> disciplineList;
  private List<Visa> visaList;
  private Integer idDocument;
  private Integer discipline;
  private Integer lengthOfStay;
  private Integer visa;
  private String email;
  private Date availableForWork;
  private Integer maxFileUploadSize;
  
  public RegistrationFormActionBean()
  {
    super();
    // Load lists.
    idDocumentList     = XmlIdDocumentDao.getInstance().selectAll();
    disciplineList   = XmlDisciplineDao.getInstance().selectAll();
    lengthOfStayList = XmlLengthOfStayDao.getInstance().selectAll();
    visaList         = XmlVisaDao.getInstance().selectAll();
    //Load required System Settings.
    SystemSettings systemSettings = XmlSystemSettingsDao.getInstance().select();
    maxFileUploadSize = systemSettings.getMaxFileUploadSize();
  }

  public Prospect getProspect()
  {
    return prospect;
  }

  public void setProspect(Prospect prospect)
  {
    this.prospect = prospect;
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

  public List<IdDocument> getIdDocumentList()
  {
    return idDocumentList;
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

  public Integer getIdDocument()
  {
    return idDocument;
  }

//  @Validate(required=true)
  public void setIdDocument(Integer idDocumentId)
  {
    this.idDocument = idDocumentId;
  }

  public Integer getLengthOfStay()
  {
    return lengthOfStay;
  }

//  @Validate(required=true)
  public void setLengthOfStay(Integer lengthOfStayId)
  {
    this.lengthOfStay = lengthOfStayId;
  }

  public Integer getDiscipline()
  {
    return discipline;
  }

//  @Validate(required=true)
  public void setDiscipline(Integer disciplineId)
  {
    this.discipline = disciplineId;
  }

  public Integer getVisa()
  {
    return visa;
  }

//  @Validate(required=true)
  public void setVisa(Integer visaId)
  {
    this.visa = visaId;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public Date getAvailableForWork()
  {
    return availableForWork;
  }

  @Validate(required=true)
  public void setAvailableForWork(Date availableForWork)
  {
    this.availableForWork = availableForWork;
  }

  public Integer getMaxFileUploadSize()
  {
    return maxFileUploadSize;
  }

  public Resolution register() throws Exception
  {
    return new ForwardResolution("/WEB-INF/jsp/" + getView().toLowerCase() + "/registration.jsp");
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    Locale locale    = getContext().getLocale();
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
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    // Set values from lists.
    prospect.setGender(gender);
    prospect.setIdDocumentId(idDocument);
    prospect.setDisciplineId(discipline);
    prospect.setVisaId(visa);
    prospect.setLengthOfStayId(lengthOfStay);
    prospect.setAvailableForWork(sdf.format(availableForWork));
    if (fileBean == null)
    {
      prospect.setDocumentFileName("");
    }
    else
    {
      String fileName = fileBean.getFileName();
//      System.out.println(fileName);
//      System.out.println(fileBean.getContentType());
//      System.out.println(fileBean.getSize());
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

  @ValidationMethod(on="save", priority=1)
  public void validatePasswordContainsSpecialCharacter(ValidationErrors errors)
  {
    if (fileBean != null)
    {
      // A file is being uploaded. Validate it.
      SystemSettings systemSettings = XmlSystemSettingsDao.getInstance().select();
      maxFileUploadSize = systemSettings.getMaxFileUploadSize();
      if (!fileBean.getContentType().equalsIgnoreCase("application/pdf") || fileBean.getSize() > (1024 * 500))
      {
        errors.add("fileBean", new SimpleError("File " + fileBean.getFileName() + " Upload FAILED."));
      }
      if (!fileBean.getContentType().equalsIgnoreCase("application/pdf"))
      {
        errors.add("fileBean", new SimpleError("It is NOT .pdf file (application/pdf). Its content type is: " + fileBean.getContentType()));
      }
      if (fileBean.getSize() > (1000 * maxFileUploadSize))
      {
        errors.add("fileBean", new SimpleError("Its size (" + (fileBean.getSize() / 1000) + "kb) exceeds maximum file size of " + maxFileUploadSize + "kb."));
      }
    }
  }
}
