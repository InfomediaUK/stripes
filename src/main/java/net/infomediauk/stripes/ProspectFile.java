package net.infomediauk.stripes;

import net.infomediauk.xml.jaxb.model.Prospect;

public class ProspectFile
{
  private String fileName;
  private String visaName;
  private String disciplineName;
  private String domicileName;
  private String lengthOfStayName;
  private Prospect prospect;

  public String getFileName()
  {
    return fileName;
  }

  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  public String getVisaName()
  {
    return visaName;
  }

  public void setVisaName(String visa)
  {
    this.visaName = visa;
  }

  public String getDisciplineName()
  {
    return disciplineName;
  }

  public String getLengthOfStayName()
  {
    return lengthOfStayName;
  }

  public void setLengthOfStayName(String lengthOfStayName)
  {
    this.lengthOfStayName = lengthOfStayName;
  }

  public void setDisciplineName(String disciplineName)
  {
    this.disciplineName = disciplineName;
  }

  public String getDomicileName()
  {
    return domicileName;
  }

  public void setDomicileName(String domicileName)
  {
    this.domicileName = domicileName;
  }

  public Prospect getProspect()
  {
    return prospect;
  }

  public void setProspect(Prospect prospect)
  {
    this.prospect = prospect;
  }
   
}
