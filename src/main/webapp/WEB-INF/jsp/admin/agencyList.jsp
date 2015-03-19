<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.AgencyActionBean" event="view">
      <s:param name="agencyName" value="${agency.fileName}" />
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.AgencyListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.AgencyListActionBean" event="refreshFromMMJ" title="Refresh from MMJ" >
      Refresh
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.AgencyListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.agencyList}" id="agency" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Code" property="code" sortable="true" />
      <d:column title="Agency" sortProperty="name" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.AgencyActionBean" event="view">
            <s:param name="id" value="${agency.id}" />
            ${agency.name}
          </s:link>
      </d:column>
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
