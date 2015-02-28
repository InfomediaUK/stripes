package net.infomediauk.xml.jaxb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//This statement means that class "DisciplineDatabase.java" is the root-element of our example
@XmlRootElement()
public class DisciplineDatabase extends BaseDatabase
{
  // XmLElementWrapper generates a wrapper element around XML representation
  @XmlElementWrapper(name = "disciplineList")
  // XmlElement
  @XmlElement(name = "discipline")
  private ArrayList<DisciplineRecord> disciplineRecordList = new ArrayList<DisciplineRecord>();
  

  private Map<Integer, DisciplineRecord> map = new HashMap<Integer, DisciplineRecord>();

  public void loadMap()
  {
    for (DisciplineRecord disciplineRecord : disciplineRecordList)
    {
      map.put(disciplineRecord.getId(), disciplineRecord);
    }
  }
  
  /**
   * @return the disciplineRecordList. NOTE. Called getDisciplineRecords NOT getDisciplineRecordList to avoid nextId conflict when running.
   */
  public ArrayList<DisciplineRecord> getDisciplineRecords()
  {
    return disciplineRecordList;
  }

  public DisciplineRecord getDisciplineRecord(Integer id)
  {
    DisciplineRecord disciplineRecord = map.get(id);
    return disciplineRecord; 
  }
  
  public void insertDisciplineRecord(DisciplineRecord disciplineRecord)
  {
    disciplineRecord.setId(getNextId());
    disciplineRecordList.add(disciplineRecord);
    map.put(disciplineRecord.getId(), disciplineRecord);
  }
  
  public void deleteDisciplineRecord(Integer id)
  {
    DisciplineRecord disciplineRecord = map.get(id);
    disciplineRecordList.remove(disciplineRecord);
    map.remove(disciplineRecord.getId());
  }
  
  /**
   * @param disciplineRecordList the disciplineRecordList to set
   */
  public void setDisciplineRecordList(ArrayList<DisciplineRecord> disciplineRecordList)
  {
    this.disciplineRecordList = disciplineRecordList;
  }

}

