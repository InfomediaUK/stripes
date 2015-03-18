package net.infomediauk.xml.jaxb.model.mmj;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class PassportTypes
{
  @XmlElement(name = "passportType")
  private ArrayList<PassportType> passportTypeList;

  public ArrayList<PassportType> getPassportTypes()
  {
    return passportTypeList;
  }

  public void setPassportTypes(ArrayList<PassportType> passportTypeList)
  {
    this.passportTypeList = passportTypeList;
  }

}
