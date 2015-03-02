package net.infomediauk.model;

public class Title extends BaseModel
{
  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((Title)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

}
