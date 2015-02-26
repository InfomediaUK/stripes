package stripesbook.action;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.infomediauk.xml.jaxb.model.HtmlPage;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public abstract class BaseActionBean implements ActionBean
{
  private ActionBeanContext actionBeanContext;
  private HtmlPage htmlPage;

  public ActionBeanContext getContext()
  {
    return actionBeanContext;
  }

  public void setContext(ActionBeanContext actionBeanContext)
  {
    this.actionBeanContext = actionBeanContext;
  }

  public HtmlPage getHtmlPage()
  {
    return htmlPage;
  }

  public void setHtmlPage(HtmlPage htmlPage)
  {
    this.htmlPage = htmlPage;
  }
  
  protected HtmlPage loadPage(String fileName)
  {
    File file = getFile(fileName);
    if (file.isFile())
    {
      try
      {
        JAXBContext jaxbContext = JAXBContext.newInstance(HtmlPage.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        HtmlPage page = (HtmlPage) jaxbUnmarshaller.unmarshal(file);
        System.out.println(page.toString());
        return page;
      }
      catch (JAXBException e)
      {
        e.printStackTrace();
      }
    }
    return new HtmlPage();
  }

  protected void savePage(HtmlPage htmlPage, String fileName)
  {
    File file = getFile(fileName);
    try
    {
      JAXBContext jaxbContext = JAXBContext.newInstance(HtmlPage.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(htmlPage, file);
      jaxbMarshaller.marshal(htmlPage, System.out);
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }

  }

  protected File getFile(String fileName)
  {
    String fullFileName = getHtmlPageFilesFolder() + "/" + fileName;;
    File file = new File(fullFileName);
    return file;
  }
  
  protected String getHtmlPageFilesFolder()
  {
//    String path = actionBeanContext.getServletContext().getRealPath("/WEB-INF/files");
    String path = System.getenv("OPENSHIFT_DATA_DIR") + "/files";
    return path;
  }
  
}
