package net.infomediauk.stripes.action.admin;

import net.infomediauk.dao.impl.XmlSystemSettingsDao;
import net.infomediauk.stripes.action.BaseActionBean;
import net.infomediauk.xml.jaxb.model.RestStatus;
import net.infomediauk.xml.jaxb.model.SystemSettings;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;

public class SystemSettingsActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/systemSettings.jsp";
  @ValidateNestedProperties({
    @Validate(field="prospectUploadBaseUrl", required=true)
  })
  private SystemSettings systemSettings;
  private String emailHost = Constants.EMAIL_HOST;
  private String exchangeServer = Constants.EXCHANGE_SERVER;
  private String originalProspectUploadBaseUrl;
  private String originalMatchMyJobRestBaseUrl;
  
  public SystemSettings getSystemSettings()
  {
    return systemSettings;
  }

  public void setSystemSettings(SystemSettings systemSettings)
  {
    this.systemSettings = systemSettings;
  }

  public String getEmailHost()
  {
    return emailHost;
  }

  public String getExchangeServer()
  {
    return exchangeServer;
  }

  public String getOriginalProspectUploadBaseUrl()
  {
    return originalProspectUploadBaseUrl;
  }

  public void setOriginalProspectUploadBaseUrl(String originalProspectUploadBaseUrl)
  {
    this.originalProspectUploadBaseUrl = originalProspectUploadBaseUrl;
  }

  public String getOriginalMatchMyJobRestBaseUrl()
  {
    return originalMatchMyJobRestBaseUrl;
  }

  public void setOriginalMatchMyJobRestBaseUrl(String originalMatchMyJobRestBaseUrl)
  {
    this.originalMatchMyJobRestBaseUrl = originalMatchMyJobRestBaseUrl;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("System Settings");
    systemSettings = XmlSystemSettingsDao.getInstance().select();
    originalProspectUploadBaseUrl = systemSettings.getProspectUploadBaseUrl();
    originalMatchMyJobRestBaseUrl = systemSettings.getMatchMyJobRestBaseUrl();
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlSystemSettingsDao.getInstance().backupDatabase();
    if (restUrlsHaveChanged())
    {
      // Rest Urls have changed. Validate them if necessary.
      ValidationErrors validationErrors = validateRestUrls();
      if (validationErrors.hasFieldErrors())
      {
        return new ForwardResolution(FORM);    
      }
    }
    XmlSystemSettingsDao.getInstance().update(systemSettings);
    getContext().getMessages().add(new SimpleMessage("Saved."));
    return new RedirectResolution(SystemSettingsActionBean.class);
  }
  
  private Boolean restUrlsHaveChanged()
  {
    return (!systemSettings.getProspectUploadBaseUrl().equals(originalProspectUploadBaseUrl) || !systemSettings.getMatchMyJobRestBaseUrl().equals(originalMatchMyJobRestBaseUrl));
  }
  
  private ValidationErrors validateRestUrls()
  {
    ValidationErrors validationErrors = getContext().getValidationErrors();
    if (!systemSettings.getProspectUploadBaseUrl().equals(originalProspectUploadBaseUrl))
    {
      // ProspectUpload has changed. Validate it.
      RestStatus restStatus = XmlSystemSettingsDao.getInstance().validateRestUrl(systemSettings.getProspectUploadBaseUrl() + "prospect/test");
      if (restStatus.getFailed())
      {
        validationErrors.add("systemSettings", new SimpleError(String.format("Prospect Upload URL wrong. Reason: %s", restStatus.getMessage())));
      }
    }
    if (!systemSettings.getMatchMyJobRestBaseUrl().equals(originalMatchMyJobRestBaseUrl))
    {
      // ProspectUpload has changed. Validate it.
      RestStatus restStatus = XmlSystemSettingsDao.getInstance().validateRestUrl(systemSettings.getMatchMyJobRestBaseUrl() + "test");
      if (restStatus.getFailed())
      {
        validationErrors.add("systemSettings", new SimpleError(String.format("MatchMyJob Rest URL wrong. Reason: %s", restStatus.getMessage())));
      }
    }
    return validationErrors;
  }
  
}
