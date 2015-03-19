<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="lengthOfStayList">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.LengthOfStayActionBean" event="view">
      <s:param name="lengthOfStayName" value="${lengthOfStay.fileName}" />
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.LengthOfStayListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.LengthOfStayListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.lengthOfStayList}" id="lengthOfStay" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="LengthOfStay" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.LengthOfStayActionBean" event="view">
            <s:param name="id" value="${lengthOfStay.id}" />
            ${lengthOfStay.name}
          </s:link>
      </d:column>
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
