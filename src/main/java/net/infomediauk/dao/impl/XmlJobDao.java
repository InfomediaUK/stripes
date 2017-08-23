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
import net.infomediauk.model.Job;
import net.infomediauk.model.Visa;
import net.infomediauk.xml.jaxb.model.JobDatabase;
import net.infomediauk.xml.jaxb.model.JobRecord;

public class XmlJobDao extends BaseDao implements Dao<Job>
{
  private static final String JOB_DATABASE_XML = "job.xml";
  private JobDatabase database;
  private static XmlJobDao instance = null;

  private XmlJobDao()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(JobDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (JobDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new JobDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlJobDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlJobDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlJobDao();
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
  public List<Job> selectAll()
  {
    List<Job> list = new ArrayList<Job>();
    Job job = null;
    if (database != null)
    {
      for (JobRecord jobRecord : database.getRecords())
      {
        job = new Job();
        fillJob(job, jobRecord);
        list.add(job);
      }
    }
    return list;
  }

  @Override
  public Job select(Integer id)
  {
    Job job = new Job();
    if (id != null)
    {
      JobRecord jobRecord = database.getRecord(id);
      fillJob(job, jobRecord);
    }
    return job;
  }

  public Job selectByName(String name)
  {
    Job job = null;
    if (database != null)
    {
      for (JobRecord jobRecord : database.getRecords())
      {
        if (jobRecord.getName().equals(name))
        {
          job = new Job();
          fillJob(job, jobRecord);
          break;
        }
      }
    }
    return job;
  }

//  public Job selectByEmail(String email)
//  {
//    Job job = null;
//    if (database != null)
//    {
//      for (JobRecord jobRecord : database.getRecords())
//      {
//        if (jobRecord.getEmail().equals(email))
//        {
//          job = new Job();
//          fillJob(job, jobRecord);
//          break;
//        }
//      }
//    }
//    return job;
//  }
//
  private void fillJob(Job job, JobRecord jobRecord)
  {
    job.setId(jobRecord.getId());
    job.setCode(jobRecord.getCode());
    job.setName(jobRecord.getName());
    job.setLocation(jobRecord.getLocation());
    job.setDescription(jobRecord.getDescription());
    job.setStartDate(jobRecord.getStartDate());
    job.setEndDate(jobRecord.getEndDate());
    job.setDisciplineId(jobRecord.getDisciplineId());
    job.setDisplayOrder(jobRecord.getDisplayOrder());
    job.setNumberOfChanges(jobRecord.getNumberOfChanges());
    // Now do the "joins"...
    if (job.getDisciplineId() != null)
    {
      Discipline discipline = XmlDisciplineDao.getInstance().select(job.getDisciplineId());
      if (discipline != null)
      {
        job.setDisciplineName(discipline.getName());
      }
    }
  }
  

  @Override
  public Boolean update(Job job)
  {
    if (job != null)
    {
      if (job.getId() == null)
      {
        JobRecord jobRecord = new JobRecord();
        fillJobRecord(jobRecord, job);
        database.insertRecord(jobRecord);
      }
      else
      {
        JobRecord jobRecord = database.getRecord(job.getId());
        if (!jobRecord.getNumberOfChanges().equals(job.getNumberOfChanges()))
        {
//          return 1;
        }
        fillJobRecord(jobRecord, job);
      }
      commit();
    }
    return true;
  }

  private void fillJobRecord(JobRecord jobRecord, Job job)
  {
    jobRecord.setCode(job.getCode());
    jobRecord.setName(job.getName());
    jobRecord.setLocation(job.getLocation());
    jobRecord.setDescription(job.getDescription());
    jobRecord.setStartDate(job.getStartDate());
    jobRecord.setEndDate(job.getEndDate());
    jobRecord.setDisciplineId(job.getDisciplineId());
    jobRecord.setDisplayOrder(job.getDisplayOrder() == null ? 0 : job.getDisplayOrder());
    jobRecord.setNumberOfChanges(job.getNumberOfChanges() + 1);
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

      File file = getFile();
      JAXBContext jaxbContext = JAXBContext.newInstance(JobDatabase.class);
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
    return JOB_DATABASE_XML;
  }
  
}
