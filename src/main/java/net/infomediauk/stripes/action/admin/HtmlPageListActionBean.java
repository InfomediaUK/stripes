package net.infomediauk.stripes.action.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.infomediauk.stripes.HtmlPageFile;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import stripesbook.action.BaseActionBean;

public class HtmlPageListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/htmlPageList.jsp";
  private List<HtmlPageFile> htmlPageFileList = new ArrayList<HtmlPageFile>();

  public List<HtmlPageFile> getHtmlPageFileList()
  {
    return htmlPageFileList;
  }

  public void setHtmlPageFileList(List<HtmlPageFile> htmlPageFileList)
  {
    this.htmlPageFileList = htmlPageFileList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    String folderName = getHtmlPageFilesFolder();
    File folder = new File(folderName);
    if (!folder.exists())
    {
      folder.mkdirs();
    }
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles)
    {
      if (file.isFile() && !file.isHidden())
      {
        // Is File and NOT hidden.
        String pageName = file.getName();
        HtmlPageFile htmlPageFile = null;
        if (pageName.lastIndexOf(".") > -1)
        {
          // File has an extension.
          if (pageName.substring(pageName.lastIndexOf(".")).equals(".xml"))
          {
            // It's an xml file.
            htmlPageFile = new HtmlPageFile();
            htmlPageFile.setFileName(file.getName());
            htmlPageFile.setHtmlPage(loadPage(pageName));
            htmlPageFileList.add(htmlPageFile);
          }
        }
      }
    }
    return new ForwardResolution(LIST);
  }  

}
