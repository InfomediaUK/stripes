package net.infomediauk.stripes;

import net.infomediauk.xml.jaxb.model.HtmlPage;

public class HtmlPageFile
{
  private String fileName;
  private HtmlPage htmlPage;

  public String getFileName()
  {
    return fileName;
  }

  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  public HtmlPage getHtmlPage()
  {
    return htmlPage;
  }

  public void setHtmlPage(HtmlPage htmlPage)
  {
    this.htmlPage = htmlPage;
  }
   
}
