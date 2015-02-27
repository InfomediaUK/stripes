package net.infomediauk.model;

public class Prospect extends ProspectShort
{
  private String discipline;
  
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
  
}
