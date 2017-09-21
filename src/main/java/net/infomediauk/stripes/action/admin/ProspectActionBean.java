package net.infomediauk.stripes.action.admin;

import java.net.MalformedURLException;
import java.util.List;

import com.sun.jersey.api.client.ClientResponse;

import net.infomediauk.dao.impl.XmlAgencyDao;
import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlPassportDao;
import net.infomediauk.dao.impl.XmlLengthOfStayDao;
import net.infomediauk.dao.impl.XmlProspectDao;
import net.infomediauk.dao.impl.XmlSystemSettingsDao;
import net.infomediauk.dao.impl.XmlTitleDao;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Agency;
import net.infomediauk.model.Discipline;
import net.infomediauk.model.Passport;
import net.infomediauk.model.LengthOfStay;
import net.infomediauk.model.Title;
import net.infomediauk.model.Visa;
import net.infomediauk.stripes.action.BaseActionBean;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.infomediauk.xml.jaxb.model.ProspectFile;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;

public class ProspectActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/prospect.jsp";
  private Prospect prospect;
  private String prospectFileName;
  // Lists
  private List<Passport> passportList;
  private List<LengthOfStay> lengthOfStayList;
  private List<Discipline> disciplineList;
  private List<Agency> agencyList;
  private List<Visa> visaList;
  private List<Title> titleList;
  // The values returned from the Lists.
  private Integer agencyId;
  private Integer passportId;
  private Integer disciplineId;
  private Integer lengthOfStayId;
  private Integer visaId;
  // Upload target URL.
  private String prospectUploadBaseUrl;

  public ProspectActionBean()
  {
    super();
    agencyList       = XmlAgencyDao.getInstance().selectAll();
    passportList     = XmlPassportDao.getInstance().selectAll();
    disciplineList   = XmlDisciplineDao.getInstance().selectAll();
    lengthOfStayList = XmlLengthOfStayDao.getInstance().selectAll();
    visaList         = XmlVisaDao.getInstance().selectAll();
    titleList        = XmlTitleDao.getInstance().selectAll();
  }

  public String getProspectFileName()
  {
    return prospectFileName;
  }

  public void setProspectFileName(String prospectFileName)
  {
    this.prospectFileName = prospectFileName;
  }

  public Prospect getProspect()
  {
    return prospect;
  }

  public void setProspect(Prospect prospectFile)
  {
    this.prospect = prospectFile;
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

  public List<Agency> getAgencyList()
  {
    return agencyList;
  }

  public void setAgencyList(List<Agency> agencyList)
  {
    this.agencyList = agencyList;
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

  public void setPassportId(Integer passportId)
  {
    this.passportId = passportId;
  }

  public void setDisciplineId(Integer disciplineId)
  {
    this.disciplineId = disciplineId;
  }

  public void setLengthOfStayId(Integer lengthOfStayId)
  {
    this.lengthOfStayId = lengthOfStayId;
  }

  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
  }

  public void setVisaId(Integer visaId)
  {
    this.visaId = visaId;
  }

  public String getProspectUploadBaseUrl()
  {
    return prospectUploadBaseUrl;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("Prospect");
    prospectUploadBaseUrl = XmlSystemSettingsDao.getInstance().select().getProspectUploadBaseUrl();
    ProspectFile prospectFile = XmlProspectDao.getInstance().select(prospectFileName);
    prospect = prospectFile.getProspect();
    return new ForwardResolution(FORM);
  }
  
  @DontValidate
  public Resolution delete()
  {
    ProspectFile prospectFile = XmlProspectDao.getInstance().select(prospectFileName);
    String prospectString = prospectFile.toString();
    if (XmlProspectDao.getInstance().delete(prospectFileName, prospectFile.getProspect().getDocumentFileName()))
    {
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", prospectString));
      return new RedirectResolution(ProspectListActionBean.class);
    }
    getContext().getMessages().add(new SimpleMessage("Unable to delete {0}.", prospectString));
    return new ForwardResolution(FORM);    
  }

  
  public Resolution save()
  {
    XmlProspectDao.getInstance().backupProspect(prospect);
    // Update Prospect from values returned from the lists.
    prospect.setPassportId(passportId);
    prospect.setDisciplineId(disciplineId);
    prospect.setVisaId(visaId);
    prospect.setLengthOfStayId(lengthOfStayId);
    XmlProspectDao.getInstance().saveProspect(prospect);
    return new RedirectResolution(ProspectListActionBean.class);
  }

  @DontValidate
  public Resolution revert()
  {
    XmlProspectDao.getInstance().revertProspect(prospect);
    return new RedirectResolution(ProspectListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(ProspectListActionBean.class);
  }

  public Resolution sendMultiPartToMmj() throws MalformedURLException
  {
    System.out.println(prospect);
    ClientResponse response = XmlProspectDao.getInstance().sendMultiPartToMmj(agencyId, prospectFileName);
    System.out.println("Back in ProspectActionBean");
    if (response.getClientResponseStatus() == ClientResponse.Status.ACCEPTED)
    {
      XmlProspectDao.getInstance().delete(prospectFileName, prospect.getDocumentFileName());
      getContext().getMessages().add(new SimpleMessage("Sent {0} to MMJ.", prospect.toString()));
      return new RedirectResolution(ProspectListActionBean.class);      
    }
    ValidationErrors validationErrors = getContext().getValidationErrors();
    validationErrors.add("prospect", new SimpleError("Unable to send {2} to MMJ. Reason: {3}", prospect.toString(), response.getEntity(String.class)));
    return new ForwardResolution(FORM);    
  }
  
//public Resolution sendToMmj()
//{
//  ProspectFile prospectFile = XmlProspectDao.getInstance().select(prospectFileName);
//  prospect = prospectFile.getProspect();
////  XmlDisciplineDao.getInstance().update(discipline);
////  getContext().getMessages().add(new SimpleMessage("Saved {0}.", discipline.getName()));
//  Client client = Client.create();
//  WebResource webResource = client.resource("http://localhost:8080/jersey/rest/prospects");
//  MultivaluedMapImpl values = new MultivaluedMapImpl();
//  values.add("title", prospect.getTitle());
//  values.add("firstName", prospect.getFirstName());
//  values.add("lastName", prospect.getLastName());
//  values.add("contactTelephone", prospect.getContactTelephone());
//  values.add("email", prospect.getEmail());
//  values.add("profession", prospect.getProfession());
//  values.add("availableForWork", prospect.getAvailableForWork());
//  values.add("disciplineId", prospect.getDisciplineId());
//  values.add("passport", prospectFile.getPassportName());
//  values.add("visaId", prospect.getVisaId());
//  values.add("lengthOfStay", prospectFile.getLengthOfStayName());
//  values.add("visaId", prospect.getVisaId());
//  values.add("documentFileName", prospect.getDocumentFileName());
//  ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, values);
//  return new RedirectResolution(ProspectListActionBean.class);
//}
//
  
}
