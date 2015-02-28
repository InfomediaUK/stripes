package net.infomediauk.model;

public class Prospect extends ProspectShort
{
  private String discipline;
  private String visa;
  
  public Prospect(ProspectShort prospectShort)
  {
    setFullName(prospectShort.getFullName());
    setContactTelephone(prospectShort.getContactTelephone());
    setEmail(prospectShort.getEmail());
    setProfession(prospectShort.getProfession());
  }

  public String getDiscipline()
  {
    return discipline;
  }

  public void setDiscipline(String discipline)
  {
    this.discipline = discipline;
  }

  public String getVisa()
  {
    return visa;
  }

  public void setVisa(String visa)
  {
    this.visa = visa;
  }
  
}
