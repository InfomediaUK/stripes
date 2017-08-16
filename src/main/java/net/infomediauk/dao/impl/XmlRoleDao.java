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
import net.infomediauk.model.Role;
import net.infomediauk.xml.jaxb.model.RoleDatabase;
import net.infomediauk.xml.jaxb.model.RoleRecord;

public class XmlRoleDao extends BaseDao implements Dao<Role>
{
  private static final String ROLE_DATABASE_XML = "role.xml";
  private RoleDatabase database;
  private static XmlRoleDao instance = null;

  private XmlRoleDao()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(RoleDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (RoleDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new RoleDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlRoleDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlRoleDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlRoleDao();
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
  public List<Role> selectAll()
  {
    List<Role> list = new ArrayList<Role>();
    Role role = null;
    if (database != null)
    {
      for (RoleRecord roleRecord : database.getRecords())
      {
        role = new Role();
        fillRole(role, roleRecord);
        list.add(role);
      }
    }
    return list;
  }

  @Override
  public Role select(Integer id)
  {
    Role role = new Role();
    if (id != null)
    {
      RoleRecord roleRecord = database.getRecord(id);
      fillRole(role, roleRecord);
    }
    return role;
  }

  private void fillRole(Role role, RoleRecord roleRecord)
  {
    role.setId(roleRecord.getId());
    role.setCode(roleRecord.getCode());
    role.setName(roleRecord.getName());
    role.setDisplayOrder(roleRecord.getDisplayOrder());
    role.setNumberOfChanges(roleRecord.getNumberOfChanges());
  }
  

  @Override
  public Boolean update(Role role)
  {
    if (role != null)
    {
      if (role.getId() == null)
      {
        RoleRecord roleRecord = new RoleRecord();
        fillRoleRecord(roleRecord, role);
        database.insertRecord(roleRecord);
      }
      else
      {
        RoleRecord roleRecord = database.getRecord(role.getId());
        if (!roleRecord.getNumberOfChanges().equals(role.getNumberOfChanges()))
        {
//          return 1;
        }
        fillRoleRecord(roleRecord, role);
      }
      commit();
    }
    return true;
  }

  private void fillRoleRecord(RoleRecord roleRecord, Role role)
  {
    roleRecord.setCode(role.getCode());
    roleRecord.setName(role.getName());
    roleRecord.setDisplayOrder(role.getDisplayOrder());
    roleRecord.setNumberOfChanges(role.getNumberOfChanges());
  }
 
  // It was probably a mistake to NOT store role id in Prospect. Hence we have to pass role itself.
  // Error prone.
  @Override
  public Boolean delete(Integer id)
  {
    return false;
  }
  
  public Boolean delete(Integer id, String role)
  {
//    if (XmlProspectDao.getInstance().roleInProspect(role))
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
      JAXBContext jaxbContext = JAXBContext.newInstance(RoleDatabase.class);
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
    return ROLE_DATABASE_XML;
  }
  
}
