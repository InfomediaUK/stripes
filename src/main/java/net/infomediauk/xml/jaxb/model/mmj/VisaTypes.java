package net.infomediauk.xml.jaxb.model.mmj;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class VisaTypes
{
  @XmlElement(name = "visaType")
  private ArrayList<VisaType> visaTypeList;

  public ArrayList<VisaType> getVisaTypes()
  {
    return visaTypeList;
  }

  public void setVisaTypes(ArrayList<VisaType> visaTypeList)
  {
    this.visaTypeList = visaTypeList;
  }

}
