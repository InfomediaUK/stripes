package net.infomediauk.stripes.Comparator;

import java.io.Serializable;
import java.util.Comparator;

import net.infomediauk.xml.jaxb.model.HtmlPageFile;

public class HtmlPageFileComparator implements Comparator<Object>, Serializable
{
  private static final long serialVersionUID = 1L;

  public int compare(Object o1, Object o2)
  {
    if (!(o1 instanceof HtmlPageFile))
      throw new ClassCastException();
    if (!(o2 instanceof HtmlPageFile))
      throw new ClassCastException();

    HtmlPageFile hpf1 = (HtmlPageFile) o1;
    HtmlPageFile hpf2 = (HtmlPageFile) o2;

    return hpf1.getFileName().compareTo(hpf2.getFileName());
  }

}
