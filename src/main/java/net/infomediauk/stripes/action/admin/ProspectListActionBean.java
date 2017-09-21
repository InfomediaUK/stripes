package net.infomediauk.stripes.action.admin;

import java.util.ArrayList;
import java.util.List;

import net.infomediauk.dao.impl.XmlProspectDao;
import net.infomediauk.stripes.action.BaseActionBean;
import net.infomediauk.xml.jaxb.model.HtmlPage;
import net.infomediauk.xml.jaxb.model.ProspectFile;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class ProspectListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/prospectList.jsp";
  private List<ProspectFile> prospectFileList = new ArrayList<ProspectFile>();
  
  public List<ProspectFile> getProspectFileList()
  {
    return prospectFileList;
  }

  public void setProspectFileList(List<ProspectFile> prospectFileList)
  {
    this.prospectFileList = prospectFileList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    prospectFileList = XmlProspectDao.getInstance().selectAll();
    setHtmlPage(new HtmlPage());
    getHtmlPage().setTitle("Prospects");
    return new ForwardResolution(LIST);
  }  

}
