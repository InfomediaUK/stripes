package net.infomediauk.model;

public class Passport extends BaseModel
{
  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((Passport)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

}