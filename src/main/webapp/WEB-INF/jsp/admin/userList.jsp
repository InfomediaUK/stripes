<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="userList">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.UserNewActionBean" event="view">
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.UserListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.UserListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.userList}" id="user" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="User Name" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.UserEditActionBean" event="view">
            <s:param name="user.id" value="${user.id}" />
            ${user.name}
          </s:link>
      </d:column>
      <d:column title="Email" property="email" sortable="true" />
      <d:column title="Password" property="password" sortable="false" />
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
