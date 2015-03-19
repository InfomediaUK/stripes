package net.infomediauk.xml.jaxb.model.mmj;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Agencies
{
  @XmlElement(name = "agency")
  private ArrayList<Agency> agencyList;

  public ArrayList<Agency> getAgencies()
  {
    return agencyList;
  }

  public void setAgencies(ArrayList<Agency> agencyList)
  {
    this.agencyList = agencyList;
  }

}
