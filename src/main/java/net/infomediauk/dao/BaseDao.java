package net.infomediauk.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BaseDao
{

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
      Files.copy(sourcePath, destinationPath);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
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
