package net.infomediauk.dao;

import java.util.List;

public interface Dao<T>
{
  public List<T> selectAll();
  public T select(Integer id);
  public Boolean update(T record);
  public Boolean delete(Integer id);
}
