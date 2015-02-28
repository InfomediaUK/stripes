package net.infomediauk.xml.jaxb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Database<T extends Identifiable>
{
  // XmLElementWrapper generates a wrapper element around XML representation
  @XmlElementWrapper(name = "list")
  @XmlElement(name = "record")
  
  private ArrayList<T> recordList = new ArrayList<T>();
  private Map<Integer, T> map = new HashMap<Integer, T>();

  public void loadMap()
  {
    for (T record : recordList)
    {
      map.put(record.getId(), record);
    }
  }
  
  /**
   * @return the recordList. NOTE. Called getRecordList NOT getList to avoid nextId conflict when running.
   */
  public ArrayList<T> getRecordList()
  {
    return recordList;
  }

  public T getRecord(Integer id)
  {
    T record = map.get(id);
    return record; 
  }
  
  public void insertRecord(T record)
  {
    record.setId(getNextId());
    recordList.add(record);
    map.put(record.getId(), record);
  }
  
  public void deleteRecord(Integer id)
  {
    T record = map.get(id);
    recordList.remove(record);
    map.remove(record.getId());
  }
  
  /**
   * @param recordList the recordList to set
   */
  public void setRecordList(ArrayList<T> recordList)
  {
    this.recordList = recordList;
  }

  @XmlElement(name = "nextId")
  private Integer nextId = new Integer(1);

  /**
   * @return the nextId
   */
  public Integer getNextId()
  {
    return nextId++;
  }

}
