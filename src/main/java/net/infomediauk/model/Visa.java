package net.infomediauk.model;

public class Visa extends NameCodeBaseModel
{
  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((Visa)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

}
