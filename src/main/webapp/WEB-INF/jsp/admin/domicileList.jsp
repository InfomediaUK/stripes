<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.DomicileActionBean">
      <s:param name="domicileName" value="${domicile.fileName}" />
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.DomicileListActionBean" event="revert">
      Revert
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.DomicileListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.DomicileListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.domicileList}" id="domicile" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Domicile" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.DomicileActionBean" event="view">
            <s:param name="id" value="${domicile.id}" />
            ${domicile.name}
          </s:link>
      </d:column>
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
