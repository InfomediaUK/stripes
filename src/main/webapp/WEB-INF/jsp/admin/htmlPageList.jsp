<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean" event="view">
      <s:param name="htmlPageFileName" value="" />
      New
    </s:link>
    <d:table name="${actionBean.htmlPageFileList}" id="htmlPageFile" requestURI="" defaultsort="1" >
      <d:column title="File" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean" event="view">
            <s:param name="htmlPageFileName" value="${htmlPageFile.fileName}" />
            ${htmlPageFile.fileName}
          </s:link>
      </d:column>
      <d:column title="Title" property="htmlPage.title" sortable="true" />
      <d:column title="Meta Description" property="htmlPage.metaDescription" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    