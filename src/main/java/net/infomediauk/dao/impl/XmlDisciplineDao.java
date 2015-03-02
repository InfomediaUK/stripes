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
import net.infomediauk.model.Discipline;
import net.infomediauk.xml.jaxb.model.DisciplineDatabase;
import net.infomediauk.xml.jaxb.model.DisciplineRecord;

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

  public void deleteData()
  {
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
    Discipline discipline = new Discipline();
    if (id != null)
    {
      DisciplineRecord disciplineRecord = database.getRecord(id);
      fillDiscipline(discipline, disciplineRecord);
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
  

  @Override
  public Boolean update(Discipline discipline)
  {
    if (discipline != null)
    {
      if (discipline.getId() == null)
      {
        DisciplineRecord disciplineRecord = new DisciplineRecord();
        fillDisciplineRecord(disciplineRecord, discipline);
        database.insertRecord(disciplineRecord);
      }
      else
      {
        DisciplineRecord disciplineRecord = database.getRecord(discipline.getId());
        if (!disciplineRecord.getNumberOfChanges().equals(discipline.getNumberOfChanges()))
        {
//          return 1;
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
    database.deleteRecord(id);
    commit();
    return true;
  }

  private void commit()
  {
    try
    {

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
  
  @Override
  public String getFileName()
  {
    return DISCIPLINE_DATABASE_XML;
  }
  
}
