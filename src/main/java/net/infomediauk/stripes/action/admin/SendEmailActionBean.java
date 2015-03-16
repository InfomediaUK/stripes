package net.infomediauk.stripes.action.admin;

import com.sendgrid.SendGrid;

import stripesbook.action.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class SendEmailActionBean extends BaseActionBean
{
  private static final String FORM = "/WEB-INF/jsp/admin/sendemail.jsp";

  @DefaultHandler
  public Resolution view() throws Exception
  {
    SendGrid sendgrid = new SendGrid("ebookshot", "ebookshot26");
    SendGrid.Email email = new SendGrid.Email();

    email.addTo("lyndon@infomediauk.net");
    email.setFrom("admin@ebookshop.com");
    email.setSubject("Sending with SendGrid is Fun");
    email.setHtml("and easy to do anywhere, even with Java");

    SendGrid.Response response = sendgrid.send(email); 
    return new ForwardResolution(FORM);
  }
  
}
