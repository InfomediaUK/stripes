<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutPlain.jsp" 
  title="Forgotten Password" 
  metaDescription="Send password hint to user's email address."
  metaKeywords="forgotten,password"
  pageHeading="forgottenPassword">
  <s:layout-component name="body">
    <p>Your password hint has been sent to your email address: ${actionBean.email}</p>
    <p>Good luck with your next login attempt!.</p>
    <p>Best Regards</p>
    <p><i>The PJ Locums Team</i></p>
    <div>
      <s:link beanclass="net.infomediauk.stripes.action.admin.LoginActionBean">
        Login
      </s:link>
    </div>
  </s:layout-component>
</s:layout-render>
