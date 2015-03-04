package net.infomediauk.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.infomediauk.model.Discipline;
import net.infomediauk.model.Domicile;
import net.infomediauk.model.LengthOfStay;
import net.infomediauk.model.Visa;
import net.infomediauk.xml.jaxb.model.Prospect;
import net.infomediauk.xml.jaxb.model.ProspectFile;

/**
 * Multiple file DAO for Prospects. That is, there can be MORE THAN ONE one file.
 */
public class XmlProspectDao
{
  private static XmlProspectDao instance = null;
  
  public static XmlProspectDao getInstance()
  {
    if (instance == null)
    {
      // NOT instantiated yet so maybe it should be created.
      synchronized(XmlProspectDao.class)
      {
        // Now that only one thread can be here check if it exists again!
        if (instance == null)
        {
          // NOT instantiated yet so create it.
          instance = new XmlProspectDao();
        }
      }
    }
    return instance;
  }

  public List<ProspectFile> selectAll()
  {
    List<ProspectFile> list = new ArrayList<ProspectFile>();
    String folderName = getProspectFilesFolder();
    File folder = new File(folderName);
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles)
    {
      if (file.isFile() && !file.isHidden())
      {
        // Is File and NOT hidden.
        String fileName = file.getName();
        ProspectFile prospectFile = null;
        if (fileName.lastIndexOf(".") > -1)
        {
          // File has an extension.
          if (fileName.substring(fileName.lastIndexOf(".")).equals(".xml"))
          {
            // It's an xml file.
            prospectFile = loadProspectFile(fileName);
            list.add(prospectFile);
          }
        }
      }
    }
    return list;
  }

  public ProspectFile select(String fileName)
  {
    ProspectFile prospectFile = loadProspectFile(fileName);
    return prospectFile;
  }
  
  private ProspectFile loadProspectFile(String fileName)
  {
    ProspectFile prospectFile = new ProspectFile();
    prospectFile.setFileName(fileName);//file.getName()
    Prospect prospect = loadProspect(fileName);
    prospectFile.setProspect(prospect);
    // Now do the "joins"...
    Visa visa = XmlVisaDao.getInstance().select(prospect.getVisaId());
    prospectFile.setVisaName(visa.getName());
    Discipline discipline = XmlDisciplineDao.getInstance().select(prospect.getDisciplineId());
    prospectFile.setDisciplineName(discipline.getName());
    Domicile domicile = XmlDomicileDao.getInstance().select(prospect.getDomicileId());
    prospectFile.setDomicileName(domicile.getName());
    LengthOfStay lengthOfStay = XmlLengthOfStayDao.getInstance().select(prospect.getLengthOfStayId());
    prospectFile.setLengthOfStayName(lengthOfStay.getName());
    return prospectFile;
  }
  
  private Prospect loadProspect(String fileName)
  {
    File file = getProspectFile(fileName);
    if (file.isFile())
    {
      try
      {
        JAXBContext jaxbContext = JAXBContext.newInstance(Prospect.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Prospect prospect = (Prospect) jaxbUnmarshaller.unmarshal(file);
        System.out.println(prospect.toString());
        return prospect;
      }
      catch (JAXBException e)
      {
        e.printStackTrace();
      }
    }
    return new Prospect();
  }

  public Boolean update(ProspectFile prospectFile)
  {
    Prospect prospect = prospectFile.getProspect();
    saveProspect(prospect);    
    return true;
  }
  
  public void saveProspect(Prospect prospect)
  {
    File file = getProspectFile(prospect.getEmail() + ".xml");
    try
    {
      JAXBContext jaxbContext = JAXBContext.newInstance(Prospect.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(prospect, file);
      jaxbMarshaller.marshal(prospect, System.out);
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }
    System.out.println(file.getName());
  }

  public Boolean disciplineInProspect(Integer id)
  {
    List<ProspectFile> prospectFileList = selectAll();
    for (ProspectFile prospectFile : prospectFileList)
    {
      if (prospectFile.getProspect().getDisciplineId().equals(id))
      {
        return true;
      }
    }
    return false;
  }
  
  public Boolean domicileInProspect(Integer id)
  {
    List<ProspectFile> prospectFileList = selectAll();
    for (ProspectFile prospectFile : prospectFileList)
    {
      if (prospectFile.getProspect().getDomicileId().equals(id))
      {
        return true;
      }
    }
    return false;
  }
  
  public Boolean visaInProspect(Integer id)
  {
    List<ProspectFile> prospectFileList = selectAll();
    for (ProspectFile prospectFile : prospectFileList)
    {
      if (prospectFile.getProspect().getVisaId().equals(id))
      {
        return true;
      }
    }
    return false;
  }
  
  public Boolean lengthOfStayInProspect(Integer id)
  {
    List<ProspectFile> prospectFileList = selectAll();
    for (ProspectFile prospectFile : prospectFileList)
    {
      if (prospectFile.getProspect().getLengthOfStayId().equals(id))
      {
        return true;
      }
    }
    return false;
  }
  
  public Boolean titleInProspect(String title)
  {
    List<ProspectFile> prospectFileList = selectAll();
    for (ProspectFile prospectFile : prospectFileList)
    {
      if (prospectFile.getProspect().getTitle().equals(title))
      {
        return true;
      }
    }
    return false;
  }
  
  private File getProspectFile(String fileName)
  {
    String fullFileName = getProspectFilesFolder() + "/" + fileName;;
    File file = new File(fullFileName);
    return file;
  }
  
  /**
   * Note that OPENSHIFT_DATA_DIR must be set as an environment variable in run configurations.
   * It must correspond to the tomcat deployment folder.
   * Eg. /Users/infomedia/Documents/Eclipse/Luna/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/stripes/WEB-INF
   * 
   * @return
   */
  private String getProspectFilesFolder()
  {
    String path = System.getenv("OPENSHIFT_DATA_DIR") + "/files/prospect";
    File folder = new File(path);
    if (!folder.exists())
    {
      folder.mkdirs();
    }
    return path;
  }

}
