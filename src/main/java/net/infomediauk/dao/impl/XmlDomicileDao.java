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
import net.infomediauk.model.Domicile;
import net.infomediauk.xml.jaxb.model.DomicileDatabase;
import net.infomediauk.xml.jaxb.model.DomicileRecord;

public class XmlDomicileDao extends BaseDao implements Dao<Domicile>
{
  private static final String DOMICILE_DATABASE_XML = "domicile.xml";
  private DomicileDatabase database;
  private static XmlDomicileDao instance = null;

  private XmlDomicileDao()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(DomicileDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (DomicileDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new DomicileDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlDomicileDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlDomicileDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlDomicileDao();
        }
      }
    }
    return instance;
  }

  public void deleteData()
  {
    database.deleteData();
  }
  
  @Override
  public List<Domicile> selectAll()
  {
    List<Domicile> list = new ArrayList<Domicile>();
    Domicile domicile = null;
    if (database != null)
    {
      for (DomicileRecord domicileRecord : database.getRecords())
      {
        domicile = new Domicile();
        fillDomicile(domicile, domicileRecord);
        list.add(domicile);
      }
    }
    return list;
  }

  @Override
  public Domicile select(Integer id)
  {
    Domicile domicile = new Domicile();
    if (id != null)
    {
      DomicileRecord domicileRecord = database.getRecord(id);
      fillDomicile(domicile, domicileRecord);
    }
    return domicile;
  }

  private void fillDomicile(Domicile domicile, DomicileRecord domicileRecord)
  {
    domicile.setId(domicileRecord.getId());
    domicile.setName(domicileRecord.getName());
    domicile.setDisplayOrder(domicileRecord.getDisplayOrder());
    domicile.setNumberOfChanges(domicileRecord.getNumberOfChanges());
  }
  

  @Override
  public Boolean update(Domicile domicile)
  {
    if (domicile != null)
    {
      if (domicile.getId() == null)
      {
        DomicileRecord domicileRecord = new DomicileRecord();
        fillDomicileRecord(domicileRecord, domicile);
        database.insertRecord(domicileRecord);
      }
      else
      {
        DomicileRecord domicileRecord = database.getRecord(domicile.getId());
        if (!domicileRecord.getNumberOfChanges().equals(domicile.getNumberOfChanges()))
        {
//          return 1;
        }
        fillDomicileRecord(domicileRecord, domicile);
      }
      commit();
    }
    return true;
  }

  private void fillDomicileRecord(DomicileRecord domicileRecord, Domicile domicile)
  {
    domicileRecord.setName(domicile.getName());
    domicileRecord.setDisplayOrder(domicile.getDisplayOrder());
    domicileRecord.setNumberOfChanges(domicile.getNumberOfChanges());
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
      JAXBContext jaxbContext = JAXBContext.newInstance(DomicileDatabase.class);
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
  
  @Override
  public String getFileName()
  {
    return DOMICILE_DATABASE_XML;
  }
  
}
