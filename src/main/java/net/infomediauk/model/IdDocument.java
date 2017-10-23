package net.infomediauk.model;

public class IdDocument extends NameCodeBaseModel
{
  private Integer idDocumentType;
  private Boolean requiresVisa;
    
  public Integer getIdDocumentType()
  {
    return idDocumentType;
  }

  public void setIdDocumentType(Integer idDocumentType)
  {
    this.idDocumentType = idDocumentType;
  }

  public String getIdDocumentTypeName()
  {
    return idDocumentType == 0 ? "Passport" : "ID Card";
  }

  public Boolean getRequiresVisa()
  {
    return requiresVisa;
  }

  public void setRequiresVisa(Boolean requiresVisa)
  {
    this.requiresVisa = requiresVisa;
  }

  @Override
  public boolean equals(Object obj)
  {
    try
    {
      return getId().equals(((IdDocument)obj).getId());
    }
    catch (Exception exc)
    {
      return false;
    }
  }

  @Override
  public String toString()
  {
    return "IdDocument [idDocumentType=" + idDocumentType + ", requiresVisa=" + requiresVisa + ", getCode()=" + getCode() + ", getName()=" + getName() + ", getId()=" + getId() + ", getDisplayOrder()="
        + getDisplayOrder() + "]";
  }

}
