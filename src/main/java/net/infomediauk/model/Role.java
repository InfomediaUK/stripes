package net.infomediauk.model;

public class Role extends NameCodeBaseModel
{
  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((Role)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

}
