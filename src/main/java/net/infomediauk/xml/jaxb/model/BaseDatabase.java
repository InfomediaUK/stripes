package net.infomediauk.xml.jaxb.model;

import javax.xml.bind.annotation.XmlElement;

public class BaseDatabase
{
  @XmlElement(name = "nextId")
  private Integer nextId = new Integer(1);

  /**
   * @return the nextId
   */
  public Integer getNextId()
  {
    return nextId++;
  }

}
