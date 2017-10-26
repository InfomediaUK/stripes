package net.infomediauk.dao.impl;

import java.io.File;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.infomediauk.dao.BaseDao;
import net.infomediauk.xml.jaxb.model.RestStatus;
import net.infomediauk.xml.jaxb.model.SystemSettings;

/**
 * Single file DAO for System Settings. That is, there is ONLY ONE file.
 * 
 * NOTE. The id value is supplied, it does NOT use nextId.
 */
public class XmlSystemSettingsDao extends BaseDao
{
  private static final String SYSTEM_SETTINGS_DATABASE_XML = "systemSettings.xml";
  private SystemSettings database;
  private static XmlSystemSettingsDao instance = null;

  @Override
  protected void loadDatabase()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(SystemSettings.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (SystemSettings)jaxbUnmarshaller.unmarshal(file);
      }
      else
      {
        database = new SystemSettings();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlSystemSettingsDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlSystemSettingsDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlSystemSettingsDao();
        }
      }
    }
    return instance;
  }

  public SystemSettings select()
  {
    return database;
  }
  
  public SystemSettings update(SystemSettings systemSettings)
  {
    this.database = systemSettings;
    commit();
    return database;
  }
  
  private void commit()
  {
    try
    {

      File file = getFile();;
      JAXBContext jaxbContext = JAXBContext.newInstance(SystemSettings.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      jaxbMarshaller.marshal(database, file);
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }

  }
  
  public RestStatus validateRestUrl(String restUrl)
  {
    RestStatus restStatus = new RestStatus();
    restStatus.setMessage("In XmlSystemSettingsDao.validateRestUrl method.");
    Client client = Client.create();
    WebResource webResource = client.resource(restUrl);
    ClientResponse response = webResource.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
    restStatus.setSuccess(response.getStatus() == 200);
    restStatus.setMessage("URL: (" + restUrl + ") - HTTP code : " + response.getStatus());
    return restStatus;
  }
  
  @Override
  public String getFileName()
  {
    return SYSTEM_SETTINGS_DATABASE_XML;
  }
  
}
