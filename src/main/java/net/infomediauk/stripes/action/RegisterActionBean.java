package net.infomediauk.stripes.action;

import stripesbook.action.BaseActionBean;
import net.infomediauk.model.Prospect;
import net.infomediauk.model.ProspectShort;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class RegisterActionBean extends BaseActionBean
{
  private ProspectShort prospectShort;
  private Prospect prospect;

  @DefaultHandler
  public Resolution start() throws Exception
  {
//    Prospect prospect = new Prospect(prospectShort);
//    prospectShort.clear();
    return new ForwardResolution("/WEB-INF/jsp/registration.jsp");
  }
}
