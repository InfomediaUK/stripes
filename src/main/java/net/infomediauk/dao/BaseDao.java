package net.infomediauk.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.StandardCopyOption.*;

public abstract class BaseDao
{

  public BaseDao()
  {
    super();
    loadDatabase();
  }

  /**
   * Delete file. Sub classes must override this method and then clear the list and map.
   */
  public void deleteData()
  {
    File file = getFile();
    file.delete();
  }

  public void backupDatabase()
  {
    String fileName = getFileName();
    String fullFileName = getDatabaseFolder() + "/" + fileName;
    String backupFullFileName = fullFileName + ".bak";
    Path sourcePath = Paths.get(fullFileName);
    Path destinationPath = Paths.get(backupFullFileName);
    try
    {
      Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void revertDatabase()
  {
    String fileName = getFileName();
    String fullFileName = getDatabaseFolder() + "/" + fileName;
    String backupFullFileName = fullFileName + ".bak";
    String tempFullFileName = fullFileName + ".tmp";
    Path databasePath = Paths.get(fullFileName);
    Path backupPath = Paths.get(backupFullFileName);
    Path tempPath = Paths.get(tempFullFileName);
    if (Files.exists(backupPath))
    {
      try
      {
        Files.move(backupPath, tempPath, REPLACE_EXISTING);
        Files.move(databasePath, backupPath, REPLACE_EXISTING);
        Files.move(tempPath, databasePath, REPLACE_EXISTING);
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    loadDatabase();
  }

  protected void loadDatabase()
  {
  }
  
  public FileInputStream getDownloadInputStream(String mimeType)
  {
    File file = getFile();
    FileInputStream fileInputStream = null;
    try
    {
      fileInputStream = new FileInputStream(file);
    }
    catch (FileNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fileInputStream;
  }
  
  public File getFile()
  {
    String fullFileName = getFullFileName();
    File file = new File(fullFileName);
    return file;
  }
 
  /**
   * Must be implemented with real database filename in sub classes.
   * 
   * @return
   */
  public String getFileName()
  {
    return "***** No Database File Name Specified *****";
  }
  
  public String getFullFileName()
  {
    return getDatabaseFolder() + "/" + getFileName();
  }
  
  /**
   * Note that OPENSHIFT_DATA_DIR must be set as an environment variable in run configurations.
   * It must correspond to the tomcat deployment folder.
   * Eg. /Users/infomedia/Documents/Eclipse/Luna/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/stripes/WEB-INF
   * 
   * @return
   */
  protected String getDatabaseFolder()
  {
    String path = System.getenv("OPENSHIFT_DATA_DIR") + "/files/database";
    File folder = new File(path);
    if (!folder.exists())
    {
      folder.mkdirs();
    }
    return path;
  }
}
