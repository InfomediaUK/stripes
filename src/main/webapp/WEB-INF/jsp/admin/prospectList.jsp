<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/admin/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
    <s:messages/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.ProspectActionBean" event="view">
      <s:param name="prospectFileName" value="" />
      New
    </s:link>
    <d:table name="${actionBean.prospectFileList}" id="prospectFile" requestURI="" defaultsort="1" >
      <d:column title="File" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.ProspectActionBean" event="view">
            <s:param name="prospectFileName" value="${prospectFile.fileName}" />
            ${prospectFile.titleAndFullName}
          </s:link>
      </d:column>
      <d:column title="Contact Tel." property="prospect.contactTelephone" sortable="true" />
      <d:column title="Email" property="prospect.email" sortable="true" />
      <d:column title="Profession" property="prospect.profession" sortable="true" />
      <d:column title="Available" property="prospect.availableForWork" sortable="true" />
      <d:column title="Visa" property="visaName" sortable="true" />
      <d:column title="Discipline" property="disciplineName" sortable="true" />
      <d:column title="Domicile" property="domicileName" sortable="true" />
      <d:column title="Length of Stay" property="lengthOfStayName" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    