package net.infomediauk.model;

public class BaseModel
{
  private Integer id;
  private String code;
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
  
  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
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

  @Override
  public String toString()
  {
    return String.format("%s", name);
  }
}
