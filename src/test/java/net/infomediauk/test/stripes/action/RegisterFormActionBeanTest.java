package net.infomediauk.test.stripes.action;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import net.infomediauk.stripes.action.RegisterFormActionBean;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockHttpSession;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;

public class RegisterFormActionBeanTest
{
  private static MockServletContext mockServletContext;
  private static MockHttpSession mockSession;
  

  @BeforeClass
  public static void setup() throws Exception
  {
    mockServletContext = new MockServletContext("stripes");
    Map<String,String> params = new HashMap<String,String>();
    params.put("ActionResolver.Packages", "stripesbook.action,net.infomediauk.stripes.action");
//    params.put("Extension.Packages", "stripesbook.ext," + "net.sourceforge.stripes.integration.spring");
    mockServletContext.addFilter(StripesFilter.class, "StripesFilter", params);
    mockServletContext.setServlet(DispatcherServlet.class, "DispatcherServlet", null);
    mockSession = new MockHttpSession(mockServletContext);
    mockServletContext.addInitParameter("localizationContext", "StripesResources");
//    ContextLoaderListener springContextLoader = new ContextLoaderListener();
//    springContextLoader.contextInitialized( new ServletContextEvent(mockServletContext));

  }
  
  @Test
  public void testEmailRequired() throws Exception
  {
    MockRoundtrip trip = new MockRoundtrip(mockServletContext, RegisterFormActionBean.class, mockSession);

    trip.execute("start");

    RegisterFormActionBean bean = trip.getActionBean(RegisterFormActionBean.class);

    assertEquals(1, bean.getContext().getValidationErrors().size());

    assertEquals(MockRoundtrip.DEFAULT_SOURCE_PAGE, trip.getDestination());
  }
  
}
