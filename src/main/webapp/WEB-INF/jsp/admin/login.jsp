<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutPlain.jsp"
  title="Login" 
  metaDescription="Login to PJ Locums Admin"
  metaKeywords="login"
    pageHeading="login">
  <s:layout-component name="body">
    <s:form beanclass="net.infomediauk.stripes.action.admin.LoginActionBean">
      <s:hidden name="interceptedUrl" />
      <s:errors />
      <div>
        <s:label for="userName" /><br />
        <s:text name="userName" id="userName" />
      </div>
      <div>
        <s:label for="userPassword" /><br />
        <s:password name="userPassword" id="userPassword" />
      </div>
      <div>
        <s:submit name="login" value="Login" />
      </div>
      <div>
        <s:link beanclass="net.infomediauk.stripes.action.admin.ForgottenPasswordActionBean">
          Forgotten Password?
        </s:link>
      </div>
    </s:form>
  </s:layout-component>
</s:layout-render>
    