package net.infomediauk.xml.jaxb.model;

public class BaseRecord
{
  private Integer id;
  private String name;
  private Integer displayOrder;
  private Integer numberOfChanges;
  
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

  public Integer getNumberOfChanges()
  {
    return numberOfChanges;
  }

  public void setNumberOfChanges(Integer numberOfChanges)
  {
    this.numberOfChanges = numberOfChanges;
  }

}
