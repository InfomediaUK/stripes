package net.infomediauk.model;

public class LengthOfStay extends NameCodeBaseModel
{
  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((LengthOfStay)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

}
