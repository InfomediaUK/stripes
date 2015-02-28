package net.infomediauk.dao;

import java.util.List;

import net.infomediauk.model.Visa;

public interface VisaDao
{
  public List<Visa> selectAll();
  public Visa select(Integer id);
  public void update(Visa visa);
  public void delete(Integer id);
}
