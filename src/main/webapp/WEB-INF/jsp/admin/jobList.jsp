<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="jobList">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.JobActionBean" event="view">
      New
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.JobListActionBean" event="delete">
      Delete
    </s:link>
    &nbsp;
    <s:link beanclass="net.infomediauk.stripes.action.admin.JobListActionBean" event="download">
      Download
    </s:link>
    <d:table name="${actionBean.jobList}" id="job" requestURI="" defaultsort="3" >
      <d:column title="Id" property="id" sortable="true" />
      <d:column title="Code" property="code" sortable="true" />
      <d:column title="Job Name" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.JobActionBean" event="view">
            <s:param name="id" value="${job.id}" />
            ${job.name}
          </s:link>
      </d:column>
      <d:column title="Location" property="location" sortable="true" />
      <d:column title="Start" property="startDate" sortable="true" />
      <d:column title="End" property="endDate" sortable="true" />
      <d:column title="Description" property="description" sortable="false" />
      <d:column title="Display Order" property="displayOrder" sortable="true" />
    </d:table>
  </s:layout-component>
</s:layout-render>
    
