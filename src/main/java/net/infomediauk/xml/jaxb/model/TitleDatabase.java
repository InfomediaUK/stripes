package net.infomediauk.xml.jaxb.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class TitleDatabase extends BaseDatabase<TitleRecord>
{
  // XmLElementWrapper generates a wrapper element around XML representation
  @XmlElementWrapper(name = "list")
  @XmlElement(name = "record")

  /**
   * This is needed for the JAXB 
   */
  @Override
  public ArrayList<TitleRecord> getRecords()
  {
    return super.getRecords();
  } 
  
}
