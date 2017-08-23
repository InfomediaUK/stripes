<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="roleList">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.RoleActionBean">
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.RoleListActionBean" event="revert">
      Revert
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.RoleListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.RoleListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.roleList}" id="role" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Code" property="code" sortable="true" />
      <d:column title="Role" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.RoleActionBean" event="view">
            <s:param name="id" value="${role.id}" />
            ${role.name}
          </s:link>
      </d:column>
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
