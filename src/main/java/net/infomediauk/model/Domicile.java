package net.infomediauk.model;

public class Domicile extends BaseModel
{
  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((Domicile)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

}
