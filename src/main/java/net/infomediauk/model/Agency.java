package net.infomediauk.model;

public class Agency extends BaseModel
{
  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((Agency)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

}
