package stripesbook.action;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import stripesbook.dao.ContactDao;
import stripesbook.dao.mock.MockContactDao;
import stripesbook.model.Contact;

public class ContactListActionBean extends BaseActionBean
{
  private static final String LIST = "/WEB-INF/jsp/contact_list.jsp";
  private ContactDao contactDao = MockContactDao.getInstance();

  @DefaultHandler
  public Resolution list()
  {
    return new ForwardResolution(LIST);
  }

  public List<Contact> getContacts()
  {
    return contactDao.read();
  }
}
