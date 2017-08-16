package net.infomediauk.stripes.action.admin;

import stripesbook.action.BaseActionBean;
import net.infomediauk.dao.impl.XmlSystemSettingsDao;
import net.infomediauk.xml.jaxb.model.SystemSettings;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

public class SystemSettingsActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/systemSettings.jsp";
  @ValidateNestedProperties({
    @Validate(field="prospectUploadBaseUrl", required=true)
  })
  private SystemSettings systemSettings;

  public SystemSettings getSystemSettings()
  {
    return systemSettings;
  }

  public void setSystemSettings(SystemSettings systemSettings)
  {
    this.systemSettings = systemSettings;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    systemSettings = XmlSystemSettingsDao.getInstance().select();
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlSystemSettingsDao.getInstance().update(systemSettings);
    getContext().getMessages().add(new SimpleMessage("Saved."));
    return new RedirectResolution(SystemSettingsActionBean.class);
  }
  
}