package net.infomediauk.xml.jaxb.model;

public class JobRecord extends NameCodeBaseRecord
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
  public String toString()
  {
    return "JobRecord [location=" + location + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + "]";
  }
  
  
}
