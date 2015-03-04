package net.infomedia.test.model;

import static org.junit.Assert.assertEquals;
import net.infomediauk.model.Domicile;

import org.junit.Test;

public class DomicileTest
{
  @Test
  public void test()
  {
    Domicile domicile1 = new Domicile();
    domicile1.setId(1);
    domicile1.setName("Name");
    domicile1.setDisplayOrder(2);
    domicile1.setNumberOfChanges(3);
    Domicile domicile2 = new Domicile();
    domicile2.setId(1);
    domicile2.setName("Name");
    domicile2.setDisplayOrder(2);
    domicile2.setNumberOfChanges(3);
    assertEquals(domicile1.getId(), new Integer(1));
    assertEquals(domicile1.getName(), "Name");
    assertEquals(domicile1.getDisplayOrder(), new Integer(2));
    assertEquals(domicile1.getNumberOfChanges(), new Integer(3));
    assertEquals(domicile1, domicile2);
  }
}
