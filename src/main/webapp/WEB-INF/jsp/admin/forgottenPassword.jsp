<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutPlain.jsp"
  title="Forgotten Password" 
  metaDescription="Send password hint to user's email address."
  metaKeywords="forgotten,password"
  pageHeading="forgottenPassword">
  <s:layout-component name="body">
    <s:form beanclass="net.infomediauk.stripes.action.admin.ForgottenPasswordActionBean">
      <s:errors />
      <div>
        <s:label for="email" /><br />
        <s:text name="email" id="email" />
      </div>
      <div>
        <s:submit name="sendPasswordHint" value="Send" />
        <s:submit name="cancel" value="Cancel" />
      </div>
    </s:form>
  </s:layout-component>
</s:layout-render>
    