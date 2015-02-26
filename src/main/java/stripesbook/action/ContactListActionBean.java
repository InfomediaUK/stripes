package stripesbook.action;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import stripesbook.dao.ContactDao;
import stripesbook.dao.mock.MockContactDao;
import stripesbook.model.Contact;

public class ContactListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/contact_list.jsp";
  private static final String VIEW = "/WEB-INF/jsp/contact_view.jsp";

  private ContactDao contactDao = MockContactDao.getInstance();
  private Integer contactId;

  public Integer getContactId()
  {
    return contactId;
  }

  public void setContactId(Integer contactId)
  {
    this.contactId = contactId;
  }

  @DefaultHandler
  public Resolution list()
  {
    return new ForwardResolution(LIST);
  }

  public Resolution view() 
  {
    return new ForwardResolution(VIEW);
  }

  public Resolution delete() 
  {
    contactDao.delete(contactId);
    return new RedirectResolution(getClass());
  }

  public Contact getContact() 
  {
    Contact contact = contactDao.read(contactId);
    return contact;
  }
  
  public List<Contact> getContacts()
  {
    return contactDao.read();
  }
}
