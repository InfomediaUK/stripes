package net.infomediauk.stripes.tag;

public class Html5InputDateTag extends Html5InputTextTag
{

  public Html5InputDateTag()
  {
    super();
    getAttributes().put("type", "date");
  }

}
