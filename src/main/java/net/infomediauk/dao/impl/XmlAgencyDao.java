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
import net.infomediauk.model.Agency;
import net.infomediauk.xml.jaxb.model.AgencyDatabase;
import net.infomediauk.xml.jaxb.model.AgencyRecord;
import net.infomediauk.xml.jaxb.model.mmj.Agencies;

/**
 * Single file DAO for Agency. That is, there is ONLY ONE file.
 * 
 * NOTE. The id value is supplied, it does NOT use nextId.
 */
public class XmlAgencyDao extends BaseDao implements Dao<Agency>
{
  private static final String AGENCY_DATABASE_XML = "agency.xml";
  private AgencyDatabase database;
  private static XmlAgencyDao instance = null;

  private XmlAgencyDao()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(AgencyDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (AgencyDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new AgencyDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlAgencyDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlAgencyDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlAgencyDao();
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
  
  public List<Agency> selectAll()
  {
    List<Agency> list = new ArrayList<Agency>();
    Agency agency = null;
    if (database != null)
    {
      for (AgencyRecord agencyRecord : database.getRecords())
      {
        agency = new Agency();
        fillAgency(agency, agencyRecord);
        list.add(agency);
      }
    }
    return list;
  }

  public Agency select(Integer id)
  {
    Agency agency = null;
    if (id != null)
    {
      AgencyRecord agencyRecord = database.getRecord(id);
      if (agencyRecord != null)
      {
        // Record found.
        agency = new Agency();
        fillAgency(agency, agencyRecord);
      }
    }
    return agency;
  }

  private void fillAgency(Agency agency, AgencyRecord agencyRecord)
  {
    agency.setId(agencyRecord.getId());
    agency.setCode(agencyRecord.getCode());
    agency.setName(agencyRecord.getName());
    agency.setDisplayOrder(agencyRecord.getDisplayOrder());
    agency.setNumberOfChanges(agencyRecord.getNumberOfChanges());
  }
  

  public Boolean update(Agency agency)
  {
    if (agency == null)
    {
      return false;
    }
    else
    {
      AgencyRecord agencyRecord = database.getRecord(agency.getId());
      if (agencyRecord == null)
      {
        // Record does NOT exist, insert a new one.
        agencyRecord = new AgencyRecord();
        fillAgencyRecord(agencyRecord, agency);
        // Insert record without getting nextId.
        agencyRecord.setId(agency.getId());
        database.insertRecord(agencyRecord, false);
      }
      else
      {
        if (!agencyRecord.getNumberOfChanges().equals(agency.getNumberOfChanges()))
        {
          return false;
        }
        fillAgencyRecord(agencyRecord, agency);
      }
      commit();
    }
    return true;
  }

  private void fillAgencyRecord(AgencyRecord agencyRecord, Agency agency)
  {
    agencyRecord.setCode(agency.getCode());
    agencyRecord.setName(agency.getName());
    agencyRecord.setDisplayOrder(agency.getDisplayOrder());
    agencyRecord.setNumberOfChanges(agency.getNumberOfChanges());
  }
  
  @Override
  public Boolean delete(Integer id)
  {
    database.deleteRecord(id);
    commit();
    return true;
  }

  private void commit()
  {
    try
    {

      File file = getFile();;
      JAXBContext jaxbContext = JAXBContext.newInstance(AgencyDatabase.class);
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
      String resource = XmlSystemSettingsDao.getInstance().select().getMatchMyJobRestBaseUrl() + "agencies";
      WebResource webResource = client.resource(resource);
//      WebResource webResource = client.resource("http://test.matchmyjob.co.uk/mmj/rest/agencies");
      ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response.getStatus() != 200)
      {
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
      }
      InputStream inputStream = response.getEntityInputStream();
      System.out.println("Output from Server .... \n");
      JAXBContext context = JAXBContext.newInstance(Agencies.class);
      Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
      Agencies agencyTypes = (Agencies)jaxbUnmarshaller.unmarshal(inputStream);
      Agency agency = null;
      Integer id = null;
      for (net.infomediauk.xml.jaxb.model.mmj.Agency agencyType : agencyTypes.getAgencies())
      {
        id = agencyType.getId();
        agency = XmlAgencyDao.getInstance().select(id);
        if (agency == null)
        {
          // Must be a new Agency.
          agency = new Agency();
          agency.setId(id);
        }
        agency.setCode(agencyType.getCode());
        agency.setName(agencyType.getName());
        agency.setDisplayOrder(agencyType.getDisplayOrder());
        XmlAgencyDao.getInstance().update(agency);
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
    return AGENCY_DATABASE_XML;
  }
  
}
