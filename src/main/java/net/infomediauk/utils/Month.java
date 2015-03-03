package net.infomediauk.utils;

public class Month
{
  private Integer id;
  private String name;
  private String localizedName;

  public Month()
  {
    super();
  }

  public Month(Integer id, String name, String localizedName)
  {
    super();
    this.id = id;
    this.name = name;
    this.localizedName = localizedName;
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

  public String getLocalizedName()
  {
    return localizedName;
  }

  public void setLocalizedName(String localizedName)
  {
    this.localizedName = localizedName;
  }

  @Override
  public String toString()
  {
    return String.format("%s", name);
  }
}
