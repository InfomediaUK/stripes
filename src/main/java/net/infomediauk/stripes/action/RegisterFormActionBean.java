package net.infomediauk.stripes.action;

import stripesbook.action.BaseActionBean;
import net.infomediauk.model.ProspectShort;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class RegisterFormActionBean extends BaseActionBean
{
  @ValidateNestedProperties({
    @Validate(field="fullName", required=true),
    @Validate(field="contactTelephone", required=true),
    @Validate(field="email", required=true)
  })
  private ProspectShort prospectShort;
  
  public ProspectShort getProspectShort()
  {
    return prospectShort;
  }

  public void setProspectShort(ProspectShort prospectShort)
  {
    this.prospectShort = prospectShort;
  }

  @DefaultHandler
  @DontValidate
  public Resolution start() throws Exception
  {
    return new ForwardResolution(RegistrationFormActionBean.class);
  }

}
