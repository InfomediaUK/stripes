package net.infomediauk.xml.jaxb.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class BaseDatabase<T extends BaseRecord>
{
  
  private ArrayList<T> recordList = new ArrayList<T>();
  private Map<Integer, T> map = new HashMap<Integer, T>();

  public void loadMap()
  {
    for (T record : recordList)
    {
      map.put(record.getId(), record);
    }
  }
  
  public void deleteData()
  {
    recordList.clear();
    map.clear();
  }
  
  /**
   * @return the recordList. NOTE. Called getRecords NOT getList to avoid nextId conflict when running.
   */
  public ArrayList<T> getRecords()
  {
    return recordList;
  }

  public T getRecord(Integer id)
  {
    T record = map.get(id);
    return record; 
  }
  
  /**
   * Insert new record after getting nextId.
   * 
   * @param record
   */
  public void insertRecord(T record)
  {
    insertRecord(record, true);
  }
  
  public void insertRecord(T record, Boolean getNextId)
  {
    if (getNextId)
    {
      record.setId(getNextId());
    }
    record.setNumberOfChanges(0);
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
