<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutLogin.jsp"
  title="Login" 
  metaDescription="Login to PJ Locums Admin"
  metaKeywords="login"
    pageHeading="login">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.LoginActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
       <td>
          <s:label for="userName" />
        </td>
        <td>
          <s:text name="userName" id="userName" />
        </td>
      </tr>
      <tr>
      <tr>
        <td>
          <s:label for="userPassword" />
        </td>
        <td>
          <s:password name="userPassword" id="userPassword" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="login" value="Login"  />
          <s:submit name="cancel" value="Cancel"  />
        </td>
      </tr>
    </table>
  </s:form>
  </s:layout-component>
</s:layout-render>
    