package net.infomediauk.xml.jaxb.model.mmj;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class IdDocumentTypes
{
  @XmlElement(name = "idDocumentType")
  private ArrayList<IdDocumentType> idDocumentTypeList;

  public ArrayList<IdDocumentType> getIdDocumentTypes()
  {
    return idDocumentTypeList;
  }

  public void setIdDocumentTypes(ArrayList<IdDocumentType> idDocumentTypeList)
  {
    this.idDocumentTypeList = idDocumentTypeList;
  }

}
