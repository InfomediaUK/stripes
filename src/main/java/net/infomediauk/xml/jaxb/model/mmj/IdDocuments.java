package net.infomediauk.xml.jaxb.model.mmj;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class IdDocuments
{
  @XmlElement(name = "idDocument")
  private ArrayList<IdDocument> idDocumentList;

  public ArrayList<IdDocument> getIdDocuments()
  {
    return idDocumentList;
  }

  public void setIdDocuments(ArrayList<IdDocument> idDocumentList)
  {
    this.idDocumentList = idDocumentList;
  }

}
