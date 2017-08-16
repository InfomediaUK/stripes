package net.infomediauk.model;

public class Discipline extends NameCodeBaseModel
{
  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((Discipline)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

}
