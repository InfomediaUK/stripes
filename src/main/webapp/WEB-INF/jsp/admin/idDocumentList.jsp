<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="idDocumentList">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.IdDocumentActionBean">
      <s:param name="idDocumentName" value="${idDocument.fileName}" />
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.IdDocumentListActionBean" event="revert">
      Revert
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.IdDocumentListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.IdDocumentListActionBean" event="refreshFromMMJ" title="Refresh from MMJ" >
      Refresh
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.IdDocumentListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.idDocumentList}" id="idDocument" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Code" property="code" sortable="true" />
      <d:column title="ID Document" sortProperty="name" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.IdDocumentActionBean" event="view">
            <s:param name="id" value="${idDocument.id}" />
            ${idDocument.name}
          </s:link>
      </d:column>
      <d:column title="ID Document Type" property="idDocumentTypeName" sortable="true" />
      <d:column title="Requires Visa" property="requiresVisa" sortable="true" />
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
