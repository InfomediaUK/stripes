package net.infomediauk.stripes.action.admin;

import java.util.List;

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
import net.infomediauk.model.Title;
import net.infomediauk.model.Visa;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.infomediauk.xml.jaxb.model.ProspectFile;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class ProspectActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/prospect.jsp";
  private Prospect prospect;
  private String prospectFileName;
  // Lists
  private List<Domicile> domicileList;
  private List<LengthOfStay> lengthOfStayList;
  private List<Discipline> disciplineList;
  private List<Visa> visaList;
  private List<Title> titleList;
  // The values returned from the Lists.
  private Integer domicileId;
  private Integer disciplineId;
  private Integer lengthOfStayId;
  private Integer visaId;
  private String title;

  public ProspectActionBean()
  {
    super();
    domicileList     = XmlDomicileDao.getInstance().selectAll();
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

  public void setVisaList(List<Visa> visaList)
  {
    this.visaList = visaList;
  }

  public List<Title> getTitleList()
  {
    return titleList;
  }

  public void setDomicileId(Integer domicileId)
  {
    this.domicileId = domicileId;
  }

  public void setDisciplineId(Integer disciplineId)
  {
    this.disciplineId = disciplineId;
  }

  public void setLengthOfStayId(Integer lengthOfStayId)
  {
    this.lengthOfStayId = lengthOfStayId;
  }

  public void setVisaId(Integer visaId)
  {
    this.visaId = visaId;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    ProspectFile prospectFile = XmlProspectDao.getInstance().select(prospectFileName);
    prospect = prospectFile.getProspect();
    return new ForwardResolution(FORM);
  }
  
  @DontValidate
  public Resolution delete()
  {
//    Prospect deletedProspect = XmlProspectDao.getInstance().select(prospectFile.getId());
//    XmlProspectDao.getInstance().delete(prospectFile.getId());
//    getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedProspect.getName()));
    return new RedirectResolution(ProspectListActionBean.class);
  }

  
  public Resolution save()
  {
//    Prospect prospect = prospectFile.getProspect();
    // Update Prospect from values returned from the lists.
    prospect.setDomicileId(domicileId);
    prospect.setDisciplineId(disciplineId);
    prospect.setVisaId(visaId);
    prospect.setLengthOfStayId(lengthOfStayId);
    prospect.setTitle(title);
    XmlProspectDao.getInstance().saveProspect(prospect);
    return new RedirectResolution(ProspectListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(ProspectListActionBean.class);
  }

}
