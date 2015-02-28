package net.infomediauk.xml.jaxb.model;

import net.infomediauk.xml.jaxb.model.Identifiable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "discipline")
public class DisciplineRecord implements Identifiable
{
  private Integer id;
  private String name;
  
  @XmlElement(name = "id")
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  /**
   * @return the name
   *
   * If you like the variable name, e.g. "name", you can easily change this
   * name for your XML-Output:
   */
  @XmlElement(name = "name")
  public String getName()
  {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name)
  {
    this.name = name;
  }

}
