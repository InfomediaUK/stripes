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
import net.infomediauk.model.IdDocument;
import net.infomediauk.xml.jaxb.model.IdDocumentDatabase;
import net.infomediauk.xml.jaxb.model.IdDocumentRecord;
import net.infomediauk.xml.jaxb.model.mmj.IdDocuments;

/**
 * Single file DAO for IdDocument Types. That is, there is ONLY ONE file.
 * 
 * NOTE. The id value is supplied, it does NOT use nextId.
 */
public class XmlIdDocumentDao extends BaseDao implements Dao<IdDocument>
{
  private static final String IDDOCUMENT_DATABASE_XML = "idDocument.xml";
  private IdDocumentDatabase database;
  private static XmlIdDocumentDao instance = null;

  @Override
  protected void loadDatabase()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(IdDocumentDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (IdDocumentDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new IdDocumentDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlIdDocumentDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlIdDocumentDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlIdDocumentDao();
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
  
  public List<IdDocument> selectAll()
  {
    List<IdDocument> list = new ArrayList<IdDocument>();
    IdDocument idDocument = null;
    if (database != null)
    {
      for (IdDocumentRecord idDocumentRecord : database.getRecords())
      {
        idDocument = new IdDocument();
        fillIdDocument(idDocument, idDocumentRecord);
        list.add(idDocument);
      }
    }
    return list;
  }

  public IdDocument select(Integer id)
  {
    IdDocument idDocument = null;
    if (id != null)
    {
      IdDocumentRecord idDocumentRecord = database.getRecord(id);
      if (idDocumentRecord != null)
      {
        // Record found.
        idDocument = new IdDocument();
        fillIdDocument(idDocument, idDocumentRecord);
      }
    }
    return idDocument;
  }

  private void fillIdDocument(IdDocument idDocument, IdDocumentRecord idDocumentRecord)
  {
    idDocument.setId(idDocumentRecord.getId());
    idDocument.setCode(idDocumentRecord.getCode());
    idDocument.setName(idDocumentRecord.getName());
    idDocument.setIdDocumentType(idDocumentRecord.getIdDocumentType());
    idDocument.setRequiresVisa(idDocumentRecord.getRequiresVisa());
    idDocument.setDisplayOrder(idDocumentRecord.getDisplayOrder());
    idDocument.setNumberOfChanges(idDocumentRecord.getNumberOfChanges());
  }
  

  public Boolean update(IdDocument idDocument)
  {
    if (idDocument == null)
    {
      return false;
    }
    else
    {
      IdDocumentRecord idDocumentRecord = database.getRecord(idDocument.getId());
      if (idDocumentRecord == null)
      {
        // Record does NOT exist, insert a new one.
        idDocumentRecord = new IdDocumentRecord();
        fillIdDocumentRecord(idDocumentRecord, idDocument);
        // Insert record without getting nextId.
        idDocumentRecord.setId(idDocument.getId());
        database.insertRecord(idDocumentRecord, false);
      }
      else
      {
        if (!idDocumentRecord.getNumberOfChanges().equals(idDocument.getNumberOfChanges()))
        {
          return false;
        }
        fillIdDocumentRecord(idDocumentRecord, idDocument);
      }
      commit();
    }
    return true;
  }

  private void fillIdDocumentRecord(IdDocumentRecord idDocumentRecord, IdDocument idDocument)
  {
    idDocumentRecord.setCode(idDocument.getCode());
    idDocumentRecord.setName(idDocument.getName());
    idDocumentRecord.setIdDocumentType(idDocument.getIdDocumentType());
    idDocumentRecord.setRequiresVisa(idDocument.getRequiresVisa());
    idDocumentRecord.setDisplayOrder(idDocument.getDisplayOrder());
    idDocumentRecord.setNumberOfChanges(idDocument.getNumberOfChanges());
  }
  
  @Override
  public Boolean delete(Integer id)
  {
    // GO AND CHECK HERE WHEN IDDOCUMENT IS ON PROSPECT *************************************
    if (XmlProspectDao.getInstance().idDocumentInProspect(id))
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
      JAXBContext jaxbContext = JAXBContext.newInstance(IdDocumentDatabase.class);
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
      String resource = XmlSystemSettingsDao.getInstance().select().getMatchMyJobRestBaseUrl() + "iddocuments";
      WebResource webResource = client.resource(resource);
      ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response.getStatus() != 200)
      {
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
      }
      InputStream inputStream = response.getEntityInputStream();
      JAXBContext context = JAXBContext.newInstance(IdDocuments.class);
      Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
      IdDocuments idDocuments = (IdDocuments)jaxbUnmarshaller.unmarshal(inputStream);
      net.infomediauk.model.IdDocument idDocument = null;
      Integer id = null;
      for (net.infomediauk.xml.jaxb.model.mmj.IdDocument jaxbIdDocument : idDocuments.getIdDocuments())
      {
        id = jaxbIdDocument.getId();
        idDocument = XmlIdDocumentDao.getInstance().select(id);
        if (idDocument == null)
        {
          // Must be a new IdDocument.
          idDocument = new IdDocument();
          idDocument.setId(id);
        }
        idDocument.setCode(jaxbIdDocument.getCode());
        idDocument.setName(jaxbIdDocument.getName());
        idDocument.setIdDocumentType(jaxbIdDocument.getIdDocumentType());
        idDocument.setRequiresVisa(jaxbIdDocument.getRequiresVisa());
        idDocument.setDisplayOrder(jaxbIdDocument.getDisplayOrder());
        XmlIdDocumentDao.getInstance().update(idDocument);
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
    return IDDOCUMENT_DATABASE_XML;
  }
  
}
