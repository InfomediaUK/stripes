package net.infomediauk.dao.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import net.infomediauk.model.Discipline;
import net.infomediauk.xml.jaxb.model.DisciplineDatabase;
import net.infomediauk.xml.jaxb.model.DisciplineRecord;
import net.infomediauk.xml.jaxb.model.mmj.DisciplineCategories;
import net.infomediauk.xml.jaxb.model.mmj.DisciplineCategory;

/**
 * Single file DAO for Discipline Types. That is, there is ONLY ONE file.
 * 
 * NOTE. The id value is supplied, it does NOT use nextId.
 */
public class XmlDisciplineDao extends BaseDao implements Dao<Discipline>
{
  private static final String DISCIPLINE_DATABASE_XML = "discipline.xml";
  private DisciplineDatabase database;
  private static XmlDisciplineDao instance = null;

  private XmlDisciplineDao()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(DisciplineDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (DisciplineDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new DisciplineDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlDisciplineDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlDisciplineDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlDisciplineDao();
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
  
  @Override
  public List<Discipline> selectAll()
  {
    List<Discipline> list = new ArrayList<Discipline>();
    Discipline discipline = null;
    if (database != null)
    {
      for (DisciplineRecord disciplineRecord : database.getRecords())
      {
        discipline = new Discipline();
        fillDiscipline(discipline, disciplineRecord);
        list.add(discipline);
      }
    }
    return list;
  }

  @Override
  public Discipline select(Integer id)
  {
    Discipline discipline = null;
    if (id != null)
    {
      DisciplineRecord disciplineRecord = database.getRecord(id);
      if (disciplineRecord != null)
      {
        // Record found.
        discipline = new Discipline();
        fillDiscipline(discipline, disciplineRecord);
      }
    }
    return discipline;
  }

  private void fillDiscipline(Discipline discipline, DisciplineRecord disciplineRecord)
  {
    discipline.setId(disciplineRecord.getId());
    discipline.setName(disciplineRecord.getName());
    discipline.setDisplayOrder(disciplineRecord.getDisplayOrder());
    discipline.setNumberOfChanges(disciplineRecord.getNumberOfChanges());
  }
  
  public Boolean update(Discipline discipline)
  {
    if (discipline == null)
    {
      return false;
    }
    else
    {
      DisciplineRecord disciplineRecord = database.getRecord(discipline.getId());
      if (disciplineRecord == null)
      {
        // Record does NOT exist, insert a new one.
        disciplineRecord = new DisciplineRecord();
        fillDisciplineRecord(disciplineRecord, discipline);
        // Insert record without getting nextId.
        disciplineRecord.setId(discipline.getId());
        database.insertRecord(disciplineRecord, false);
      }
      else
      {
        if (!disciplineRecord.getNumberOfChanges().equals(discipline.getNumberOfChanges()))
        {
          return false;
        }
        fillDisciplineRecord(disciplineRecord, discipline);
      }
      commit();
    }
    return true;
  }

  private void fillDisciplineRecord(DisciplineRecord disciplineRecord, Discipline discipline)
  {
    disciplineRecord.setName(discipline.getName());
    disciplineRecord.setDisplayOrder(discipline.getDisplayOrder());
    disciplineRecord.setNumberOfChanges(discipline.getNumberOfChanges());
  }
  
  @Override
  public Boolean delete(Integer id)
  {
    if (XmlProspectDao.getInstance().disciplineInProspect(id))
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
      Collections.sort(database.getRecords(), new Comparator<DisciplineRecord>() 
      {
        @Override
        public int compare(DisciplineRecord  disciplineRecord1, DisciplineRecord  disciplineRecord2)
        {
          if (disciplineRecord1.getDisplayOrder().equals(disciplineRecord2.getDisplayOrder()))
          {
            return  disciplineRecord1.getName().compareTo(disciplineRecord2.getName());
          }
          return  disciplineRecord1.getDisplayOrder().compareTo(disciplineRecord2.getDisplayOrder());
        }
      });      
      File file = getFile();;
      JAXBContext jaxbContext = JAXBContext.newInstance(DisciplineDatabase.class);
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
//      WebResource webResource = client.resource("http://localhost:8080/jersey/rest/disciplines");
      WebResource webResource = client.resource("http://test.matchmyjob.co.uk/mmj/rest/disciplines");
      ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response.getStatus() != 200)
      {
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
      }
      InputStream inputStream = response.getEntityInputStream();
      System.out.println("Output from Server .... \n");
      JAXBContext context = JAXBContext.newInstance(DisciplineCategories.class);
      Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
      DisciplineCategories disciplineCategories = (DisciplineCategories)jaxbUnmarshaller.unmarshal(inputStream);
      Discipline discipline = null;
      Integer id = null;
      for (DisciplineCategory disciplineCategory : disciplineCategories.getDisciplineCategories())
      {
        id = disciplineCategory.getId();
        discipline = XmlDisciplineDao.getInstance().select(id);
        if (discipline == null)
        {
          // Must be a new Discipline Category.
          discipline = new Discipline();
          discipline.setId(id);
        }
        discipline.setName(disciplineCategory.getName());
        discipline.setDisplayOrder(disciplineCategory.getDisplayOrder());
        XmlDisciplineDao.getInstance().update(discipline);
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
    return DISCIPLINE_DATABASE_XML;
  }
  
}
