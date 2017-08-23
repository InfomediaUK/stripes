package net.infomediauk.model;

public class Job extends NameCodeBaseModel
{
  private String location;
  private String description;
  private String startDate;
  private String endDate;

  public String getLocation()
  {
    return location;
  }
  
  public void setLocation(String location)
  {
    this.location = location;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getStartDate()
  {
    return startDate;
  }

  public void setStartDate(String startDate)
  {
    this.startDate = startDate;
  }

  public String getEndDate()
  {
    return endDate;
  }

  public void setEndDate(String endDate)
  {
    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((Job)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

  @Override
  public String toString()
  {
    return "Job [location=" + location + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + "]";
  }

}
