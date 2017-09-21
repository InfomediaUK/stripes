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
import net.infomediauk.model.Passport;
import net.infomediauk.xml.jaxb.model.PassportDatabase;
import net.infomediauk.xml.jaxb.model.PassportRecord;
import net.infomediauk.xml.jaxb.model.mmj.PassportTypes;
import net.infomediauk.xml.jaxb.model.mmj.PassportType;

/**
 * Single file DAO for Passport Types. That is, there is ONLY ONE file.
 * 
 * NOTE. The id value is supplied, it does NOT use nextId.
 */
public class XmlPassportDao extends BaseDao implements Dao<Passport>
{
  private static final String PASSPORT_DATABASE_XML = "passport.xml";
  private PassportDatabase database;
  private static XmlPassportDao instance = null;

  @Override
  protected void loadDatabase()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(PassportDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (PassportDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new PassportDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlPassportDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlPassportDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlPassportDao();
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
  
  public List<Passport> selectAll()
  {
    List<Passport> list = new ArrayList<Passport>();
    Passport passport = null;
    if (database != null)
    {
      for (PassportRecord passportRecord : database.getRecords())
      {
        passport = new Passport();
        fillPassport(passport, passportRecord);
        list.add(passport);
      }
    }
    return list;
  }

  public Passport select(Integer id)
  {
    Passport passport = null;
    if (id != null)
    {
      PassportRecord passportRecord = database.getRecord(id);
      if (passportRecord != null)
      {
        // Record found.
        passport = new Passport();
        fillPassport(passport, passportRecord);
      }
    }
    return passport;
  }

  private void fillPassport(Passport passport, PassportRecord passportRecord)
  {
    passport.setId(passportRecord.getId());
    passport.setCode(passportRecord.getCode());
    passport.setName(passportRecord.getName());
    passport.setDisplayOrder(passportRecord.getDisplayOrder());
    passport.setNumberOfChanges(passportRecord.getNumberOfChanges());
  }
  

  public Boolean update(Passport passport)
  {
    if (passport == null)
    {
      return false;
    }
    else
    {
      PassportRecord passportRecord = database.getRecord(passport.getId());
      if (passportRecord == null)
      {
        // Record does NOT exist, insert a new one.
        passportRecord = new PassportRecord();
        fillPassportRecord(passportRecord, passport);
        // Insert record without getting nextId.
        passportRecord.setId(passport.getId());
        database.insertRecord(passportRecord, false);
      }
      else
      {
        if (!passportRecord.getNumberOfChanges().equals(passport.getNumberOfChanges()))
        {
          return false;
        }
        fillPassportRecord(passportRecord, passport);
      }
      commit();
    }
    return true;
  }

  private void fillPassportRecord(PassportRecord passportRecord, Passport passport)
  {
    passportRecord.setCode(passport.getCode());
    passportRecord.setName(passport.getName());
    passportRecord.setDisplayOrder(passport.getDisplayOrder());
    passportRecord.setNumberOfChanges(passport.getNumberOfChanges());
  }
  
  @Override
  public Boolean delete(Integer id)
  {
    // GO AND CHECK HERE WHEN PASSPORT IS ON PROSPECT *************************************
    if (XmlProspectDao.getInstance().passportInProspect(id))
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
      JAXBContext jaxbContext = JAXBContext.newInstance(PassportDatabase.class);
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
  
  public void refreshFromWeb()
  {
    try
    {
      Client client = Client.create();
      String resource = XmlSystemSettingsDao.getInstance().select().getMatchMyJobRestBaseUrl() + "passports";
      WebResource webResource = client.resource(resource);
      ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response.getStatus() != 200)
      {
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
      }
      InputStream inputStream = response.getEntityInputStream();
      JAXBContext context = JAXBContext.newInstance(PassportTypes.class);
      Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
      PassportTypes passportTypes = (PassportTypes)jaxbUnmarshaller.unmarshal(inputStream);
      Passport passport = null;
      Integer id = null;
      for (PassportType passportType : passportTypes.getPassportTypes())
      {
        id = passportType.getId();
        passport = XmlPassportDao.getInstance().select(id);
        if (passport == null)
        {
          // Must be a new Passport.
          passport = new Passport();
          passport.setId(id);
        }
        passport.setCode(passportType.getCode());
        passport.setName(passportType.getName());
        passport.setDisplayOrder(passportType.getDisplayOrder());
        XmlPassportDao.getInstance().update(passport);
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
    return PASSPORT_DATABASE_XML;
  }
  
}
