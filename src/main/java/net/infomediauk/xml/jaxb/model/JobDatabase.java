package net.infomediauk.xml.jaxb.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class JobDatabase extends BaseDatabase<JobRecord>
{
  // XmLElementWrapper generates a wrapper element around XML representation
  @XmlElementWrapper(name = "list")
  @XmlElement(name = "record")

  /**
   * This is needed for the JAXB 
   */
  @Override
  public ArrayList<JobRecord> getRecords()
  {
    return super.getRecords();
  } 
  
}
