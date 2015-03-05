<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/admin/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
    <s:messages/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.DisciplineActionBean" event="view">
      <s:param name="disciplineName" value="${discipline.fileName}" />
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.DisciplineListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.DisciplineListActionBean" event="refreshFromMMJ" title="Refresh from MMJ" >
      Refresh
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.DisciplineListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.disciplineList}" id="discipline" requestURI="" defaultsort="2" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Discipline" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.DisciplineActionBean" event="view">
            <s:param name="id" value="${discipline.id}" />
            ${discipline.name}
          </s:link>
      </d:column>
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
