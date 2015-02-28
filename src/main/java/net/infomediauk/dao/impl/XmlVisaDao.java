package net.infomediauk.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.infomediauk.dao.BaseDao;
import net.infomediauk.dao.VisaDao;
import net.infomediauk.model.Visa;
import net.infomediauk.xml.jaxb.model.VisaDatabase;
import net.infomediauk.xml.jaxb.model.VisaRecord;

public class XmlVisaDao extends BaseDao implements VisaDao
{
  private static final String VISA_DATABASE_XML = "visa.xml";
  private VisaDatabase visaDatabase;
  private static XmlVisaDao instance = null;

  private XmlVisaDao()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(VisaDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        visaDatabase = (VisaDatabase)jaxbUnmarshaller.unmarshal(file);
        visaDatabase.loadMap();
      }
      else
      {
        visaDatabase = new VisaDatabase();
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
      synchronized(XmlDisciplineDao.class)
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
  public List<Visa> selectAll()
  {
    List<Visa> list = new ArrayList<Visa>();
    Visa visa = null;
    if (visaDatabase != null)
    {
      for (VisaRecord visaRecord : visaDatabase.getVisaRecords())
      {
        visa = new Visa();
        visa.setId(visaRecord.getId());
        visa.setName(visaRecord.getName());
        list.add(visa);
      }
    }
    return list;
  }

  @Override
  public Visa select(Integer id)
  {
    Visa visa = new Visa();
    if (id != null)
    {
      VisaRecord visaRecord = visaDatabase.getVisaRecord(id);
      visa.setId(visaRecord.getId());
      visa.setName(visaRecord.getName());
    }
    return visa;
  }

  @Override
  public void update(Visa visa)
  {
    if (visa != null)
    {
      if (visa.getId() == null)
      {
        VisaRecord visaRecord = new VisaRecord();
        visaRecord.setName(visa.getName());
        visaDatabase.insertVisaRecord(visaRecord);
      }
      else
      {
        VisaRecord visaRecord = visaDatabase.getVisaRecord(visa.getId());
        visaRecord.setName(visa.getName());
      }
      commit();
    }
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

      jaxbMarshaller.marshal(visaDatabase, file);
      jaxbMarshaller.marshal(visaDatabase, System.out);
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }

  }
  
  @Override
  public void delete(Integer id)
  {
    visaDatabase.deleteVisaRecord(id);
    commit();
  }

  @Override
  public String getFileName()
  {
    return VISA_DATABASE_XML;
  }
  
}
