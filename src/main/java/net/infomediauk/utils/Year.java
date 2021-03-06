package net.infomediauk.utils;

public class Year
{
  private Integer id;
  private String name;

  public Year()
  {
    super();
  }

  public Year(Integer id, String name)
  {
    super();
    this.id = id;
    this.name = name;
  }

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
  public String toString()
  {
    return String.format("%s", name);
  }
}
