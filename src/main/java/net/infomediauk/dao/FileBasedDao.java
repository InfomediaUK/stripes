package net.infomediauk.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class FileBasedDao
{

  /**
   * Delete file. Sub classes must override this method and then clear the list and map.
   */
  public void deleteData()
  {
    File file = getFile();
    file.delete();
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
