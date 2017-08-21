<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="user">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.UserEditActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          Id
        </td>
        <td>
          ${actionBean.user.id} 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="user.name" />
        </td>
        <td>
          <s:text name="user.name" id="user.name" class="required" />
        </td>
      </tr>
      <tr>
       <td>
          <s:label for="email" />
        </td>
        <td>
          <s:text name="user.email" id="email" class="required" />
        </td>
      </tr>
      <tr>
       <td>
          <s:label for="passwordHint" />
        </td>
        <td>
          <s:text name="user.passwordHint" id="passwordHint" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="displayOrder" />
        </td>
        <td>
          <s:text name="user.displayOrder" id="displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
          <s:submit name="delete" value="Delete"  />
        </td>
      </tr>
      <tr>
        <td>
          <s:link beanclass="net.infomediauk.stripes.action.admin.UserChangePasswordActionBean">
            <s:param name="user.id" value="${actionBean.user.id}" />
            Change Password
          </s:link>
        </td>
      </tr>          
    </table>
    <s:hidden name="user.id" />
    <s:hidden name="user.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    