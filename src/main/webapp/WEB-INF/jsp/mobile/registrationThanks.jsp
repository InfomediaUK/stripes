<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/mobile/layout/layoutMain.jsp" 
  title="Thanks for Registering" 
  metaDescription="register"
  metaKeywords="job" >
  <s:layout-component name="body">
    <p>Thanks for registering with us ${actionBean.prospect.firstName}, it's appreciated.</p>
    <p>If we can help you find a job we'll get back to you as soon as possible...</p>
    <p>Best Regards</p>
    <p><i>The PJ Locums Team</i></p>
  </s:layout-component>
</s:layout-render>
