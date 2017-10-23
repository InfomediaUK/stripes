package net.infomediauk.xml.jaxb.model;

public class IdDocumentRecord extends NameCodeBaseRecord
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

  public Boolean getRequiresVisa()
  {
    return requiresVisa;
  }

  public void setRequiresVisa(Boolean requiresVisa)
  {
    this.requiresVisa = requiresVisa;
  }

  @Override
  public String toString()
  {
    return "IdDocumentRecord [idDocumentType=" + idDocumentType + ", requiresVisa=" + requiresVisa + ", getCode()=" + getCode() + ", getName()=" + getName() + ", getId()=" + getId()
        + ", getDisplayOrder()=" + getDisplayOrder() + "]";
  }

}
