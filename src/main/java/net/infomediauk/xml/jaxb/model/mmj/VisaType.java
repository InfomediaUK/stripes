package net.infomediauk.xml.jaxb.model.mmj;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement()
@XmlType(propOrder = { "id", "name", "displayOrder" })
public class VisaType
{
  private Integer id;
  private String name;
  private Integer displayOrder;

  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }

  public Integer getDisplayOrder()
  {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder)
  {
    this.displayOrder = displayOrder;
  }

  @Override
  public String toString()
  {
    return String.format("%s", name);
  }

}
