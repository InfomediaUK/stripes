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
import net.infomediauk.model.User;
import net.infomediauk.xml.jaxb.model.UserDatabase;
import net.infomediauk.xml.jaxb.model.UserRecord;

public class XmlUserDao extends BaseDao implements Dao<User>
{
  private static final String USER_DATABASE_XML = "user.xml";
  private UserDatabase database;
  private static XmlUserDao instance = null;

  @Override
  protected void loadDatabase()
  {
    try
    {
      File file = getFile();
      if (file.exists())
      {
        // Read in database.
        JAXBContext context = JAXBContext.newInstance(UserDatabase.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        database = (UserDatabase)jaxbUnmarshaller.unmarshal(file);
        database.loadMap();
      }
      else
      {
        database = new UserDatabase();
        commit();
      }
    }
    catch (JAXBException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static XmlUserDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlUserDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlUserDao();
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
  public List<User> selectAll()
  {
    List<User> list = new ArrayList<User>();
    User user = null;
    if (database != null)
    {
      for (UserRecord userRecord : database.getRecords())
      {
        user = new User();
        fillUser(user, userRecord);
        list.add(user);
      }
    }
    return list;
  }

  @Override
  public User select(Integer id)
  {
    User user = new User();
    if (id != null)
    {
      UserRecord userRecord = database.getRecord(id);
      fillUser(user, userRecord);
    }
    return user;
  }

  public User selectByName(String name)
  {
    User user = null;
    if (database != null)
    {
      for (UserRecord userRecord : database.getRecords())
      {
        if (userRecord.getName().equals(name))
        {
          user = new User();
          fillUser(user, userRecord);
          break;
        }
      }
    }
    return user;
  }

  public User selectByEmail(String email)
  {
    User user = null;
    if (database != null)
    {
      for (UserRecord userRecord : database.getRecords())
      {
        if (userRecord.getEmail().equals(email))
        {
          user = new User();
          fillUser(user, userRecord);
          break;
        }
      }
    }
    return user;
  }

  private void fillUser(User user, UserRecord userRecord)
  {
    user.setId(userRecord.getId());
    user.setName(userRecord.getName());
    user.setEmail(userRecord.getEmail());
    user.setPassword(userRecord.getPassword());
    user.setPasswordHint(userRecord.getPasswordHint());
    user.setDisplayOrder(userRecord.getDisplayOrder());
    user.setNumberOfChanges(userRecord.getNumberOfChanges());
  }
  

  @Override
  public Boolean update(User user)
  {
    if (user != null)
    {
      if (user.getId() == null)
      {
        UserRecord userRecord = new UserRecord();
        fillUserRecord(userRecord, user);
        database.insertRecord(userRecord);
      }
      else
      {
        UserRecord userRecord = database.getRecord(user.getId());
        if (!userRecord.getNumberOfChanges().equals(user.getNumberOfChanges()))
        {
//          return 1;
        }
        fillUserRecord(userRecord, user);
      }
      commit();
    }
    return true;
  }

  private void fillUserRecord(UserRecord userRecord, User user)
  {
    userRecord.setName(user.getName());
    userRecord.setEmail(user.getEmail());
    if (user.getId() == null)
    {
      // The Password is only ever set here for NEW Users.
      userRecord.setPassword(user.getPassword());
    }
    userRecord.setPasswordHint(user.getPasswordHint());
    userRecord.setDisplayOrder(user.getDisplayOrder() == null ? 0 : user.getDisplayOrder());
    userRecord.setNumberOfChanges(user.getNumberOfChanges() + 1);
  }
  
  public Boolean updatePassword(User user)
  {
    UserRecord userRecord = database.getRecord(user.getId());
    if (!userRecord.getNumberOfChanges().equals(user.getNumberOfChanges()))
    {
      return false;
    }
    userRecord.setPassword(user.getPassword());
    userRecord.setPasswordHint(user.getPasswordHint());
    commit();
    return true;
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
      JAXBContext jaxbContext = JAXBContext.newInstance(UserDatabase.class);
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
    return USER_DATABASE_XML;
  }
  
}
