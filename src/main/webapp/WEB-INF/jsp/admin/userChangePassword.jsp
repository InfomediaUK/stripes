<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="user">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.UserChangePasswordActionBean">
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
          User Name
        </td>
        <td>
          ${actionBean.user.name} 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="oldPassword" />
        </td>
        <td>
          <s:password name="oldPassword" id="oldPassword" repopulate="true" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="newPassword" />
        </td>
        <td>
          <s:password name="newPassword" id="newPassword" repopulate="true" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="confirmPassword" />
        </td>
        <td>
          <s:password name="confirmPassword" id="confirmPassword" repopulate="true" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        </td>
      </tr>
    </table>
    <s:hidden name="user.id" />
    <s:hidden name="user.numberOfChanges" />
    <%-- These fields are required to pass common validation. Only the password is updated by this action. --%>
    <s:hidden name="user.name" />
    <s:hidden name="user.email" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    