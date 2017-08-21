<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutPlain.jsp"
  title="Forgotten Password" 
  metaDescription="Send password hint to user's email address."
  metaKeywords="forgotten,password"
  pageHeading="forgottenPassword">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.ForgottenPasswordActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
       <td>
          <s:label for="email" />
        </td>
        <td>
          <s:text name="email" id="email" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="sendPasswordHint" value="Send"  />
          <s:submit name="cancel" value="Cancel"  />
        </td>
      </tr>
    </table>
  </s:form>
  </s:layout-component>
</s:layout-render>
    