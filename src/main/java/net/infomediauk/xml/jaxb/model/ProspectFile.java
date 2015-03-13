package net.infomediauk.xml.jaxb.model;

public class ProspectFile
{
  private Prospect prospect;
  private String fileName;
  private String visaName;
  private String disciplineName;
  private String domicileName;
  private String lengthOfStayName;

  public ProspectFile()
  {
    super();
  }

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
   
  public String getTitleAndFullName()
  {
    return prospect.getTitleAndFullName();
  }
  
  public String getFullName()
  {
    return prospect.getFullName();
  }

  @Override
  public String toString()
  {
    return prospect.toString() + " (" + fileName + ")";
  }
}
