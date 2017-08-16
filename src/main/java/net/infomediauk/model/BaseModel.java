package net.infomediauk.model;

public class BaseModel
{
  private Integer id;
  private Integer displayOrder = 0;
  private Integer numberOfChanges = 0;
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
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
    return String.format("%s", id);
  }
}
