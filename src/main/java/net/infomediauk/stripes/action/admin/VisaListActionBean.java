package net.infomediauk.stripes.action.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.infomediauk.dao.impl.XmlTitleDao;
import net.infomediauk.dao.impl.XmlVisaDao;
import net.infomediauk.model.Visa;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import stripesbook.action.BaseActionBean;

public class VisaListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/admin/visaList.jsp";
  private List<Visa> visaList;

  public List<Visa> getVisaList()
  {
    return visaList;
  }

  public void setVisaList(List<Visa> visaList)
  {
    this.visaList = visaList;
  }

  @DefaultHandler
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    visaList = XmlVisaDao.getInstance().selectAll();
    return new ForwardResolution(LIST);
  }  

  public Resolution delete()
  {
    XmlVisaDao.getInstance().deleteData();
    return new ForwardResolution(LIST);
  }
  
  /**
   * See http://stripes.sourceforge.net/docs/current/javadoc/net/sourceforge/stripes/action/StreamingResolution.html
   * @return
   */
  public Resolution download()
  {
    String fileName = XmlTitleDao.getInstance().getFileName();
    String mimeType = getContext().getServletContext().getMimeType(fileName);
    FileInputStream inputStream = XmlVisaDao.getInstance().getDownloadInputStream(mimeType);
    return new StreamingResolution(mimeType, inputStream).setFilename(fileName);
  }

  public Resolution getMmjVisaTypes()
  {
    String data = processSimpleHTTPGet("http://localhost:8080/stripes/test2.html");
    return new RedirectResolution(VisaListActionBean.class);
  }
  
  private String processSimpleHTTPGet(String url)
  {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    try
    {
      HttpGet httpget = new HttpGet(url);

      System.out.println("Executing request " + httpget.getRequestLine());

      // Create a custom response handler
      ResponseHandler<String> responseHandler = new ResponseHandler<String>()
      {

        @Override
        public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException
        {
          int status = response.getStatusLine().getStatusCode();
          if (status >= 200 && status < 300)
          {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
          }
          else
          {
            throw new ClientProtocolException("Unexpected response status: " + status);
          }
        }

      };
      String responseBody = httpclient.execute(httpget, responseHandler);
      System.out.println("----------------------------------------");
      System.out.println(responseBody);
      return responseBody;
    }
    catch (ClientProtocolException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    finally
    {
      try
      {
        httpclient.close();
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return "FUCKED";
  }

}
