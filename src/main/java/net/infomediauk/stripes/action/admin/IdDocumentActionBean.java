package net.infomediauk.stripes.action.admin;

import net.infomediauk.dao.impl.XmlIdDocumentDao;
import net.infomediauk.model.IdDocument;
import net.infomediauk.stripes.action.BaseActionBean;
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

public class IdDocumentActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/idDocument.jsp";
  @ValidateNestedProperties({
    @Validate(field="id", required=true),
    @Validate(field="name", required=true)
  })
  private IdDocument idDocument;
  private Integer id;

  public IdDocument getIdDocument()
  {
    return idDocument;
  }

  public void setIdDocument(IdDocument idDocument)
  {
    this.idDocument = idDocument;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  @DefaultHandler
  @DontValidate
  public Resolution view() throws Exception
  {
    setHtmlPage(loadPage(this.getClass().getSimpleName() + ".xml"));
    getHtmlPage().setTitle("IdDocument");
    idDocument = XmlIdDocumentDao.getInstance().select(id);
    return new ForwardResolution(FORM);
  }
  
  public Resolution create()
  {
    XmlIdDocumentDao.getInstance().update(idDocument);
    return new ForwardResolution(FORM);
  }
    
  @DontValidate
  public Resolution delete()
  {
    IdDocument deletedIdDocument = XmlIdDocumentDao.getInstance().select(idDocument.getId());
    if (XmlIdDocumentDao.getInstance().delete(idDocument.getId()))
    {
      // Deleted successfully.
      getContext().getMessages().add(new SimpleMessage("Deleted {0}.", deletedIdDocument.getName()));
      return new RedirectResolution(IdDocumentListActionBean.class);
    }
    ValidationErrors validationErrors = getContext().getValidationErrors();
    validationErrors.add("idDocument.name", new SimpleError("Is in existing Prospect. Cannot delete it"));
    return new ForwardResolution(FORM);
  }
  
  public Resolution save()
  {
    XmlIdDocumentDao.getInstance().backupDatabase();
    XmlIdDocumentDao.getInstance().update(idDocument);
    getContext().getMessages().add(new SimpleMessage("Saved {0}.", idDocument.getName()));
    return new RedirectResolution(IdDocumentListActionBean.class);
  }
  
  @DontValidate
  public Resolution cancel()
  {
    return new RedirectResolution(IdDocumentListActionBean.class);
  }

}
