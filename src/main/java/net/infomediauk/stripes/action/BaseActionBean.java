package net.infomediauk.stripes.action;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }

  }
  
  protected void backupPage(String fileName)
  {
    String fullFileName = getHtmlPageFilesFolder() + "/" + fileName;
    Path sourcePath = Paths.get(fullFileName);
    if (Files.exists(sourcePath))
    {
      // The file exists, so back it up.
      String backupFullFileName = fullFileName + ".bak";
      Path destinationPath = Paths.get(backupFullFileName);
      try
      {
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      } 
    }
    else
    {
      // The file does not exist. It is a NEW page yet to be saved.
    }
  }

  protected void revertPage(String fileName)
  {
    String pageFullFileName = getHtmlPageFilesFolder() + "/" + fileName;
    String backupFullFileName = pageFullFileName + ".bak";
    String tempFullFileName = pageFullFileName + ".tmp";
    Path prospectPath = Paths.get(pageFullFileName);
    Path backupPath = Paths.get(backupFullFileName);
    Path tempPath = Paths.get(tempFullFileName);
    if (Files.exists(backupPath))
    {
      try
      {
        Files.move(backupPath, tempPath, REPLACE_EXISTING);
        Files.move(prospectPath, backupPath, REPLACE_EXISTING);
        Files.move(tempPath, prospectPath, REPLACE_EXISTING);
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
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
