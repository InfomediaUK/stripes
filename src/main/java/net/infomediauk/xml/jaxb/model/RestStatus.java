package net.infomediauk.xml.jaxb.model;

public class RestStatus
{
  private Boolean success;
  private String message;

  public RestStatus()
  {
    super();
    success = false;
  }

  public Boolean getSuccess()
  {
    return success;
  }

  public void setSuccess(Boolean success)
  {
    this.success = success;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public Boolean getFailed()
  {
    return !success;
  }

  @Override
  public String toString()
  {
    return "RestStatus [success=" + success + ", message=" + message + "]";
  }
    
}
