<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="role">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.RoleActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          Id
        </td>
        <td>
          ${actionBean.role.id} 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="code" />
        </td>
        <td>
          <s:text name="role.code" id="code" size="6" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="name" />
        </td>
        <td>
          <s:text name="role.name" id="name" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="displayOrder" />
        </td>
        <td>
          <s:text name="role.displayOrder" id="displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.role.id!=null}">
          <s:submit name="delete" value="Delete"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="role.id" />
    <s:hidden name="role.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    