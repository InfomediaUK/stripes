package net.infomediauk.stripes.action.admin;

import java.util.ArrayList;
import java.util.List;

import net.infomediauk.dao.impl.XmlProspectDao;
import net.infomediauk.stripes.ProspectFile;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import stripesbook.action.BaseActionBean;

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
    return new ForwardResolution(LIST);
  }  

}
