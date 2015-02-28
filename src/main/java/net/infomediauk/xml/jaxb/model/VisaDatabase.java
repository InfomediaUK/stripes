package net.infomediauk.xml.jaxb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//This statement means that class "VisaDatabase.java" is the root-element of our example
@XmlRootElement()
public class VisaDatabase extends BaseDatabase
{
  // XmLElementWrapper generates a wrapper element around XML representation
  @XmlElementWrapper(name = "visaList")
  // XmlElement
  @XmlElement(name = "visa")
  private ArrayList<VisaRecord> visaRecordList = new ArrayList<VisaRecord>();
  

  private Map<Integer, VisaRecord> map = new HashMap<Integer, VisaRecord>();

  public void loadMap()
  {
    for (VisaRecord visaRecord : visaRecordList)
    {
      map.put(visaRecord.getId(), visaRecord);
    }
  }
  
  /**
   * @return the visaRecordList. NOTE. Called getVisaRecords NOT getVisaRecordList to avoid nextId conflict when running.
   */
  public ArrayList<VisaRecord> getVisaRecords()
  {
    return visaRecordList;
  }

  public VisaRecord getVisaRecord(Integer id)
  {
    VisaRecord visaRecord = map.get(id);
    return visaRecord; 
  }
  
  public void insertVisaRecord(VisaRecord visaRecord)
  {
    visaRecord.setId(getNextId());
    visaRecordList.add(visaRecord);
    map.put(visaRecord.getId(), visaRecord);
  }
  
  public void deleteVisaRecord(Integer id)
  {
    VisaRecord visaRecord = map.get(id);
    visaRecordList.remove(visaRecord);
    map.remove(visaRecord.getId());
  }
  
  /**
   * @param visaRecordList the visaRecordList to set
   */
  public void setVisaRecordList(ArrayList<VisaRecord> visaRecordList)
  {
    this.visaRecordList = visaRecordList;
  }

}

