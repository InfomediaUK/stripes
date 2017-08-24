package net.infomediauk.xml.jaxb.model;


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
   
  public String getShortName()
  {
    int i = fileName.indexOf("ActionBean.xml");
    return fileName.substring(0, i);
  }

}
