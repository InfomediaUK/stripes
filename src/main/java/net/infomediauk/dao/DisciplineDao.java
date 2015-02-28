package net.infomediauk.dao;

import java.util.List;

import net.infomediauk.model.Discipline;

public interface DisciplineDao
{
  public List<Discipline> selectAll();
  public Discipline select(Integer id);
  public void update(Discipline discipline);
  public void delete(Integer id);
}
