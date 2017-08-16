<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.TitleActionBean" event="view">
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.TitleListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.TitleListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.titleList}" id="title" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Title" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.TitleActionBean" event="view">
            <s:param name="id" value="${title.id}" />
            ${title.name}
          </s:link>
      </d:column>
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
