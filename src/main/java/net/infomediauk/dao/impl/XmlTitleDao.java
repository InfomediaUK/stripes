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
import net.infomediauk.model.Title;
import net.infomediauk.xml.jaxb.model.TitleDatabase;
import net.infomediauk.xml.jaxb.model.TitleRecord;

public class XmlTitleDao extends BaseDao implements Dao<Title>
{
  private static final String TITLE_DATABASE_XML = "title.xml";
  private TitleDatabase database;
  private static XmlTitleDao instance = null;

  @Override
  protected void loadDatabase()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(TitleDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (TitleDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new TitleDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlTitleDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlTitleDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlTitleDao();
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
  public List<Title> selectAll()
  {
    List<Title> list = new ArrayList<Title>();
    Title title = null;
    if (database != null)
    {
      for (TitleRecord titleRecord : database.getRecords())
      {
        title = new Title();
        fillTitle(title, titleRecord);
        list.add(title);
      }
    }
    return list;
  }

  @Override
  public Title select(Integer id)
  {
    Title title = new Title();
    if (id != null)
    {
      TitleRecord titleRecord = database.getRecord(id);
      fillTitle(title, titleRecord);
    }
    return title;
  }

  private void fillTitle(Title title, TitleRecord titleRecord)
  {
    title.setId(titleRecord.getId());
    title.setName(titleRecord.getName());
    title.setDisplayOrder(titleRecord.getDisplayOrder());
    title.setNumberOfChanges(titleRecord.getNumberOfChanges());
  }
  

  @Override
  public Boolean update(Title title)
  {
    if (title != null)
    {
      if (title.getId() == null)
      {
        TitleRecord titleRecord = new TitleRecord();
        fillTitleRecord(titleRecord, title);
        database.insertRecord(titleRecord);
      }
      else
      {
        TitleRecord titleRecord = database.getRecord(title.getId());
        if (!titleRecord.getNumberOfChanges().equals(title.getNumberOfChanges()))
        {
//          return 1;
        }
        fillTitleRecord(titleRecord, title);
      }
      commit();
    }
    return true;
  }

  private void fillTitleRecord(TitleRecord titleRecord, Title title)
  {
    titleRecord.setName(title.getName());
    titleRecord.setDisplayOrder(title.getDisplayOrder());
    titleRecord.setNumberOfChanges(title.getNumberOfChanges());
  }
 
  // It was probably a mistake to NOT store title id in Prospect. Hence we have to pass title itself.
  // Error prone.
  @Override
  public Boolean delete(Integer id)
  {
    return false;
  }
  
  public Boolean delete(Integer id, String title)
  {
//    if (XmlProspectDao.getInstance().titleInProspect(title))
//    {
//      return false;
//    }
    database.deleteRecord(id);
    commit();
    return true;
  }

  private void commit()
  {
    try
    {

      File file = getFile();;
      JAXBContext jaxbContext = JAXBContext.newInstance(TitleDatabase.class);
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
    return TITLE_DATABASE_XML;
  }
  
}
