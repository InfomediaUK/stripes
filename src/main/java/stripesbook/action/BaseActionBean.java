package stripesbook.action;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.handinteractive.mobile.UAgentInfo;

import net.infomediauk.stripes.ext.SessionActionBeanContext;
import net.infomediauk.xml.jaxb.model.HtmlPage;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.util.Base64;

public abstract class BaseActionBean implements ActionBean
{
  private SessionActionBeanContext actionBeanContext;
  private HtmlPage htmlPage;
  private String currentActionBeanClassName = this.getClass().getName();
  
  public SessionActionBeanContext getContext()
  {
    return actionBeanContext;
  }

  public void setContext(ActionBeanContext actionBeanContext)
  {
    this.actionBeanContext = (SessionActionBeanContext)actionBeanContext;
  }

  public String getLastUrl()
  {
    HttpServletRequest req = getContext().getRequest();
    StringBuilder sb = new StringBuilder();

    // Start with the URI and the path
    String uri = (String)req.getAttribute("javax.servlet.forward.request_uri");
    String path = (String)req.getAttribute("javax.servlet.forward.path_info");
    if (uri == null)
    {
      uri = req.getRequestURI();
      path = req.getPathInfo();
    }
    String context = req.getContextPath();
    if (context != null)
    {
      if (uri.startsWith(context))
      {
        // Remove context from front of uri.
        uri = uri.substring(context.length());
      }
    }
    sb.append(uri);
    if (path != null)
    {
      sb.append(path);
    }

    // Now the request parameters
    sb.append('?');
    Map<String, String[]> map = new HashMap<String, String[]>(req.getParameterMap());

    // Remove previous locale parameter, if present.
    // map.remove(MyLocalePicker.LOCALE);

    // Append the parameters to the URL
    for (String key : map.keySet())
    {
      String[] values = map.get(key);
      for (String value : values)
      {
        sb.append(key).append('=').append(value).append('&');
      }
    }
    // Remove the last character '?' or '&'.
    sb.deleteCharAt(sb.length() - 1);

    return sb.toString();
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
   * 
   * @return
   */
  protected String getHtmlPageFilesFolder()
  {
    String path = System.getenv("OPENSHIFT_DATA_DIR") + "/files/static";
    return path;
  }

  protected String encryptPassword(String password)
  {
    try
    {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] bytes = md.digest(password.getBytes());
      return Base64.encodeBytes(bytes);
    }
    catch (NoSuchAlgorithmException exc)
    {
      throw new IllegalArgumentException(exc);
    }
  }
}
