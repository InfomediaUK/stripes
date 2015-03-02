package net.infomediauk.stripes.action;

import java.io.File;
import java.io.IOException;
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
    @Validate(field="email", required=true, converter=EmailTypeConverter.class)
  })
  private Prospect prospect;
  private ProspectShort prospectShort;
  private FileBean fileBean;
  private List<Discipline> disciplineList;
  private List<Visa> visaList;
  private Integer disciplineId;
  private Integer visaId;
  
  public RegistrationActionBean()
  {
    super();
    disciplineList = XmlDisciplineDao.getInstance().selectAll();
    visaList = XmlVisaDao.getInstance().selectAll();
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
    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
  }

  public Resolution save()
  {
    prospect.setDisciplineId(disciplineId);
    prospect.setVisaId(visaId);
    if (fileBean != null)
    {
      String fileName = fileBean.getFileName();
      System.out.println(fileName);
      System.out.println(fileBean.getContentType());
      System.out.println(fileBean.getSize());
      String newFileName = prospect.getEmail() + fileName.substring(fileName.lastIndexOf("."));
      String newFullFileName = getProspectFolder() + "/" + newFileName; 
      prospect.setDocumentFileName(newFileName);
    }
//    try
//    {
//      fileBean.save(new File(getProspectFolder() + "/" + newFileName));
//    }
//    catch (IOException e)
//    {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }

    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
//    return new RedirectResolution(RegisterActionBean.class);
  }

  /**
   * Note that OPENSHIFT_DATA_DIR must be set as an environment variable in run configurations.
   * It must correspond to the tomcat deployment folder.
   * Eg. /Users/infomedia/Documents/Eclipse/Luna/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/stripes/WEB-INF
   * 
   * @return
   */
  protected String getProspectFolder()
  {
    String path = System.getenv("OPENSHIFT_DATA_DIR") + "/files/prospect";
    File folder = new File(path);
    if (!folder.exists())
    {
      folder.mkdirs();
    }
    return path;
  }

//  @ValidationMethod(on={"save"})
//  public void repopulateList()
//  {
//    disciplineList = XmlDisciplineDao.getInstance().selectAll();
//    visaList = XmlVisaDao.getInstance().selectAll();
//  }
}
