<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="visaList">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.VisaActionBean" event="view">
      <s:param name="visaName" value="${visa.fileName}" />
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.VisaListActionBean" event="revert">
      Revert
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.VisaListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.VisaListActionBean" event="refreshFromMMJ" title="Refresh from MMJ" >
      Refresh
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.VisaListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.visaList}" id="visa" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Code" property="code" sortable="true" />
      <d:column title="Visa" sortProperty="name" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.VisaActionBean" event="view">
            <s:param name="id" value="${visa.id}" />
            ${visa.name}
          </s:link>
      </d:column>
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
