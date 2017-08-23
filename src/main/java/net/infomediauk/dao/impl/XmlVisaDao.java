package net.infomediauk.dao.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.infomediauk.dao.BaseDao;
import net.infomediauk.dao.Dao;
import net.infomediauk.model.Visa;
import net.infomediauk.xml.jaxb.model.VisaDatabase;
import net.infomediauk.xml.jaxb.model.VisaRecord;
import net.infomediauk.xml.jaxb.model.mmj.VisaTypes;
import net.infomediauk.xml.jaxb.model.mmj.VisaType;

/**
 * Single file DAO for Visa Types. That is, there is ONLY ONE file.
 * 
 * NOTE. The id value is supplied, it does NOT use nextId.
 */
public class XmlVisaDao extends BaseDao implements Dao<Visa>
{
  private static final String VISA_DATABASE_XML = "visa.xml";
  private VisaDatabase database;
  private static XmlVisaDao instance = null;

  @Override
  protected void loadDatabase()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(VisaDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (VisaDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new VisaDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlVisaDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlVisaDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlVisaDao();
        }
      }
    }
    return instance;
  }

  @Override
  public void deleteData()
  {
    super.deleteData();
    database.deleteData();
  }
  
  public List<Visa> selectAll()
  {
    List<Visa> list = new ArrayList<Visa>();
    Visa visa = null;
    if (database != null)
    {
      for (VisaRecord visaRecord : database.getRecords())
      {
        visa = new Visa();
        fillVisa(visa, visaRecord);
        list.add(visa);
      }
    }
    return list;
  }

  public Visa select(Integer id)
  {
    Visa visa = null;
    if (id != null)
    {
      VisaRecord visaRecord = database.getRecord(id);
      if (visaRecord != null)
      {
        // Record found.
        visa = new Visa();
        fillVisa(visa, visaRecord);
      }
    }
    return visa;
  }

  private void fillVisa(Visa visa, VisaRecord visaRecord)
  {
    visa.setId(visaRecord.getId());
    visa.setCode(visaRecord.getCode());
    visa.setName(visaRecord.getName());
    visa.setDisplayOrder(visaRecord.getDisplayOrder());
    visa.setNumberOfChanges(visaRecord.getNumberOfChanges());
  }
  

  public Boolean update(Visa visa)
  {
    if (visa == null)
    {
      return false;
    }
    else
    {
      VisaRecord visaRecord = database.getRecord(visa.getId());
      if (visaRecord == null)
      {
        // Record does NOT exist, insert a new one.
        visaRecord = new VisaRecord();
        fillVisaRecord(visaRecord, visa);
        // Insert record without getting nextId.
        visaRecord.setId(visa.getId());
        database.insertRecord(visaRecord, false);
      }
      else
      {
        if (!visaRecord.getNumberOfChanges().equals(visa.getNumberOfChanges()))
        {
          return false;
        }
        fillVisaRecord(visaRecord, visa);
      }
      commit();
    }
    return true;
  }

  private void fillVisaRecord(VisaRecord visaRecord, Visa visa)
  {
    visaRecord.setCode(visa.getCode());
    visaRecord.setName(visa.getName());
    visaRecord.setDisplayOrder(visa.getDisplayOrder());
    visaRecord.setNumberOfChanges(visa.getNumberOfChanges());
  }
  
  @Override
  public Boolean delete(Integer id)
  {
    if (XmlProspectDao.getInstance().visaInProspect(id))
    {
      return false;
    }
    database.deleteRecord(id);
    commit();
    return true;
  }

  private void commit()
  {
    try
    {

      File file = getFile();;
      JAXBContext jaxbContext = JAXBContext.newInstance(VisaDatabase.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      jaxbMarshaller.marshal(database, file);
      jaxbMarshaller.marshal(database, System.out);
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }

  }
  
  public void refreshFromWeb()
  {
    try
    {
      Client client = Client.create();
      String resource = XmlSystemSettingsDao.getInstance().select().getMatchMyJobRestBaseUrl() + "visas";
      WebResource webResource = client.resource(resource);
      ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response.getStatus() != 200)
      {
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
      }
      InputStream inputStream = response.getEntityInputStream();
      System.out.println("Output from Server .... \n");
      JAXBContext context = JAXBContext.newInstance(VisaTypes.class);
      Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
      VisaTypes visaTypes = (VisaTypes)jaxbUnmarshaller.unmarshal(inputStream);
      Visa visa = null;
      Integer id = null;
      for (VisaType visaType : visaTypes.getVisaTypes())
      {
        id = visaType.getId();
        visa = XmlVisaDao.getInstance().select(id);
        if (visa == null)
        {
          // Must be a new Visa.
          visa = new Visa();
          visa.setId(id);
        }
        visa.setCode(visaType.getCode());
        visa.setName(visaType.getName());
        visa.setDisplayOrder(visaType.getDisplayOrder());
        XmlVisaDao.getInstance().update(visa);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
  }
  
  @Override
  public String getFileName()
  {
    return VISA_DATABASE_XML;
  }
  
}
