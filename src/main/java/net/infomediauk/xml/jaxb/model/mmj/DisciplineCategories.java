package net.infomediauk.xml.jaxb.model.mmj;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class DisciplineCategories
{
  @XmlElement(name = "disciplineCategory")
  private ArrayList<DisciplineCategory> disciplineCategoryList;

  public ArrayList<DisciplineCategory> getDisciplineCategories()
  {
    return disciplineCategoryList;
  }

  public void setDisciplineCategories(ArrayList<DisciplineCategory> disciplineCategoryList)
  {
    this.disciplineCategoryList = disciplineCategoryList;
  }

}
