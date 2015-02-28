<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/admin/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.VisaActionBean" event="view">
      <s:param name="visaName" value="${visa.fileName}" />
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.VisaListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.visaList}" id="visa" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Visa" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.VisaActionBean" event="view">
            <s:param name="id" value="${visa.id}" />
            ${visa.name}
          </s:link>
      </d:column>
    </d:table>
  </s:layout-component>
</s:layout-render>
    
