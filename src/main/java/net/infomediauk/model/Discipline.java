package net.infomediauk.model;

public class Discipline
{
  private Integer id;
  private String name;
  
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

  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return id.equals(((Discipline)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

  @Override
  public String toString()
  {
    return String.format("%s", name);
  }
}
