package net.infomediauk.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.infomediauk.dao.BaseDao;
import net.infomediauk.dao.Dao;
import net.infomediauk.model.LengthOfStay;
import net.infomediauk.xml.jaxb.model.LengthOfStayDatabase;
import net.infomediauk.xml.jaxb.model.LengthOfStayRecord;

public class XmlLengthOfStayDao extends BaseDao implements Dao<LengthOfStay>
{
  private static final String LENGTH_OF_STAY_DATABASE_XML = "lengthOfStay.xml";
  private LengthOfStayDatabase database;
  private static XmlLengthOfStayDao instance = null;

  @Override
  protected void loadDatabase()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(LengthOfStayDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (LengthOfStayDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new LengthOfStayDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlLengthOfStayDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlLengthOfStayDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlLengthOfStayDao();
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
  public List<LengthOfStay> selectAll()
  {
    List<LengthOfStay> list = new ArrayList<LengthOfStay>();
    LengthOfStay lengthOfStay = null;
    if (database != null)
    {
      for (LengthOfStayRecord lengthOfStayRecord : database.getRecords())
      {
        lengthOfStay = new LengthOfStay();
        fillLengthOfStay(lengthOfStay, lengthOfStayRecord);
        list.add(lengthOfStay);
      }
    }
    return list;
  }

  @Override
  public LengthOfStay select(Integer id)
  {
    LengthOfStay lengthOfStay = new LengthOfStay();
    if (id != null)
    {
      LengthOfStayRecord lengthOfStayRecord = database.getRecord(id);
      fillLengthOfStay(lengthOfStay, lengthOfStayRecord);
    }
    return lengthOfStay;
  }

  private void fillLengthOfStay(LengthOfStay lengthOfStay, LengthOfStayRecord lengthOfStayRecord)
  {
    lengthOfStay.setId(lengthOfStayRecord.getId());
    lengthOfStay.setName(lengthOfStayRecord.getName());
    lengthOfStay.setDisplayOrder(lengthOfStayRecord.getDisplayOrder());
    lengthOfStay.setNumberOfChanges(lengthOfStayRecord.getNumberOfChanges());
  }
  

  @Override
  public Boolean update(LengthOfStay lengthOfStay)
  {
    if (lengthOfStay != null)
    {
      if (lengthOfStay.getId() == null)
      {
        LengthOfStayRecord lengthOfStayRecord = new LengthOfStayRecord();
        fillLengthOfStayRecord(lengthOfStayRecord, lengthOfStay);
        database.insertRecord(lengthOfStayRecord);
      }
      else
      {
        LengthOfStayRecord lengthOfStayRecord = database.getRecord(lengthOfStay.getId());
        if (!lengthOfStayRecord.getNumberOfChanges().equals(lengthOfStay.getNumberOfChanges()))
        {
//          return 1;
        }
        fillLengthOfStayRecord(lengthOfStayRecord, lengthOfStay);
      }
      commit();
    }
    return true;
  }

  private void fillLengthOfStayRecord(LengthOfStayRecord lengthOfStayRecord, LengthOfStay lengthOfStay)
  {
    lengthOfStayRecord.setName(lengthOfStay.getName());
    lengthOfStayRecord.setDisplayOrder(lengthOfStay.getDisplayOrder());
    lengthOfStayRecord.setNumberOfChanges(lengthOfStay.getNumberOfChanges());
  }
  
  @Override
  public Boolean delete(Integer id)
  {
    if (XmlProspectDao.getInstance().lengthOfStayInProspect(id))
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

      File file = getFile();
      JAXBContext jaxbContext = JAXBContext.newInstance(LengthOfStayDatabase.class);
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
  
  @Override
  public String getFileName()
  {
    return LENGTH_OF_STAY_DATABASE_XML;
  }
  
}
