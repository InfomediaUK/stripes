package net.infomediauk.stripes.tag;

import net.sourceforge.stripes.tag.InputTextTag;

public class Html5InputTextTag extends InputTextTag
{

  public void setRequired(String required)
  {
    set("required", required);
  }

  public String getRequired()
  {
    return get("required");
  }

  public String getPlaceholder()
  {
    return get("placeholder");
  }

  public void setPlaceholder(String placeholder)
  {
    set("placeholder", placeholder);
  }

  public String getPattern()
  {
    return get("pattern");
  }

  public void setPattern(String pattern)
  {
    set("pattern", pattern);
  }
}
