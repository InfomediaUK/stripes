package net.infomediauk.stripes.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.model.Discipline;
import net.infomediauk.stripes.action.BaseActionBean;
import net.infomediauk.xml.jaxb.model.HtmlPage;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class HtmlPageActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/htmlPage.jsp";
  @ValidateNestedProperties({
    @Validate(field="title", required=true),
    @Validate(field="metaDescription", required=true)
  })
  private HtmlPage htmlPageToEdit;
  private String htmlPageFileName;
  // List
  private List<Discipline> disciplineList;
  // The value returned from the List.
  private Integer disciplineId;

  public String getHtmlPageFileName()
  {
    return htmlPageFileName;
  }

  @Validate(required=true)
  public void setHtmlPageFileName(String htmlPageFileName)
  {
    this.htmlPageFileName = htmlPageFileName;
  }

  public HtmlPage getHtmlPageToEdit()
  {
    return htmlPageToEdit;
  }

  public void setHtmlPageToEdit(HtmlPage htmlPageToEdit)
  {
    this.htmlPageToEdit = htmlPageToEdit;
  }

  public List<Discipline> getDisciplineList()
  {
    return disciplineList;
  }

  public void setDisciplineId(Integer relatedDisciplineId)
  {
    this.disciplineId = relatedDisciplineId;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    disciplineList = XmlDisciplineDao.getInstance().selectAll();
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("HTML Page");
    setHtmlPageToEdit(loadPage(htmlPageFileName));
    return new ForwardResolution(FORM);
  }
  
//  @DontValidate
//  public Resolution create()
//  {
//    htmlPageToEdit = new HtmlPage();
//    return new ForwardResolution(FORM);
//  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  @DontValidate
  public Resolution download()
  {
    File file = getHtmlPageFile(htmlPageFileName);
    String mimeType = getContext().getServletContext().getMimeType(htmlPageFileName);
    FileInputStream inputStream = null;
    try
    {
      inputStream = new FileInputStream(file);
    }
    catch (FileNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new StreamingResolution(mimeType, inputStream).setFilename("htmlPageFileName");
  }
  
  @DontValidate
  public Resolution delete()
  {
    File file = getHtmlPageFile(htmlPageFileName);
    file.delete();
    return new RedirectResolution(HtmlPageListActionBean.class);
  }
  
  public Resolution save()
  {
    backupPage(htmlPageFileName);
    htmlPageToEdit.setRelatedDisciplineId(disciplineId);
    savePage(htmlPageToEdit, htmlPageFileName);
    return new RedirectResolution(HtmlPageListActionBean.class);
  }

  @DontValidate
  public Resolution revert()
  {
    revertPage(htmlPageFileName);
    return new RedirectResolution(HtmlPageListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(HtmlPageListActionBean.class);
  }

}
