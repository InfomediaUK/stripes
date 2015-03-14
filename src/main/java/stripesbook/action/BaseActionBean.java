package stripesbook.action;

import java.io.File;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.handinteractive.mobile.UAgentInfo;

import net.infomediauk.xml.jaxb.model.HtmlPage;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public abstract class BaseActionBean implements ActionBean
{
  private ActionBeanContext actionBeanContext;
  private HtmlPage htmlPage;
  private String currentActionBeanClassName = this.getClass().getName();
  
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
  
  public String getCurrentActionBeanClassName()
  {
    return currentActionBeanClassName;
  }

  public void setCurrentActionBeanClassName(String currentActionBeanClassName)
  {
    this.currentActionBeanClassName = currentActionBeanClassName;
  }

  protected String getView()
  {
    HttpSession session = getContext().getRequest().getSession();
    if (session.getAttribute("view") == null)
    {
      UAgentInfo uAgentInfo = new UAgentInfo(getContext().getRequest().getHeader("User-Agent"), getContext().getRequest().getHeader("Accept"));
      if (uAgentInfo.detectMobileLong())
      {
        session.setAttribute("view", "MOBILE");
      }
      else
      {
        session.setAttribute("view", "SITE");
      }
    }
    return (String)session.getAttribute("view");
  }
  
  protected HtmlPage loadPage(String fileName)
  {
    File file = getHtmlPageFile(fileName);
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
    File file = getHtmlPageFile(fileName);
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
  
  protected File getHtmlPageFile(String fileName)
  {
    String fullFileName = getHtmlPageFilesFolder() + "/" + fileName;;
    File file = new File(fullFileName);
    return file;
  }
  
  /**
   * Note that OPENSHIFT_DATA_DIR must be set as an environment variable in run configurations.
   * It must correspond to the tomcat deployment folder.
   * Eg. /Users/infomedia/Documents/Eclipse/Luna/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/stripes/WEB-INF
   * 
   * @return
   */
  protected String getHtmlPageFilesFolder()
  {
    String path = System.getenv("OPENSHIFT_DATA_DIR") + "/files/static";
    return path;
  }

}
