package net.infomediauk.stripes.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import stripesbook.action.BaseActionBean;
import net.infomediauk.xml.jaxb.model.HtmlPage;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class HtmlPageActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/htmlPage.jsp";
  private String htmlPageFileName;
  private HtmlPage htmlPageToEdit;

  public String getHtmlPageFileName()
  {
    return htmlPageFileName;
  }

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

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    setHtmlPageToEdit(loadPage(htmlPageFileName));
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    htmlPageToEdit = new HtmlPage();
    return new ForwardResolution(FORM);
  }
  
  public Resolution download()
  {
    File file = getFile(htmlPageFileName);
    HttpServletResponse response = getContext().getResponse();
    String mimeType = getContext().getServletContext().getMimeType(htmlPageFileName);
    response.setContentType(mimeType);
    response.setContentLength((int)file.length());
    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
    response.setHeader(headerKey, headerValue);
    try
    {
      FileInputStream inputStream = new FileInputStream(file);
      int read=0;
      byte[] bytes = new byte[4096];
      OutputStream outputStream = response.getOutputStream();
      try
      {
        while((read = inputStream.read(bytes))!= -1)
        {
          outputStream.write(bytes, 0, read);
        }
      }
      finally
      {
        inputStream.close();
        outputStream.flush();
        outputStream.close();
      }
    }
    catch (FileNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new ForwardResolution(FORM);
  }
  
  public Resolution delete()
  {
    File file = getFile(htmlPageFileName);
    file.delete();
    return new RedirectResolution(HtmlPageListActionBean.class);
  }
  
  public Resolution save()
  {
    savePage(htmlPageToEdit, htmlPageFileName);
    return new RedirectResolution(HtmlPageListActionBean.class);
  }
  
  public Resolution cancel()
  {
    return new RedirectResolution(HtmlPageListActionBean.class);
  }

}
