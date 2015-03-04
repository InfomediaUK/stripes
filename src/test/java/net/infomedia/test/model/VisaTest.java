package net.infomedia.test.model;

import static org.junit.Assert.assertEquals;
import net.infomediauk.model.Visa;

import org.junit.Test;

public class VisaTest
{
  @Test
  public void test()
  {
    Visa visa1 = new Visa();
    visa1.setId(1);
    visa1.setName("Name");
    visa1.setDisplayOrder(2);
    visa1.setNumberOfChanges(3);
    Visa visa2 = new Visa();
    visa2.setId(1);
    visa2.setName("Name");
    visa2.setDisplayOrder(2);
    visa2.setNumberOfChanges(3);
    assertEquals(visa1.getId(), new Integer(1));
    assertEquals(visa1.getName(), "Name");
    assertEquals(visa1.getDisplayOrder(), new Integer(2));
    assertEquals(visa1.getNumberOfChanges(), new Integer(3));
    assertEquals(visa1, visa2);
  }
}
