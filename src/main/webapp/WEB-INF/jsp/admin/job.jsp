<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="job">
  <s:layout-component name="body">
    <s:form beanclass="net.infomediauk.stripes.action.admin.JobActionBean">
      <s:errors />
      <div>
        Id<br /> 
        ${actionBean.job.id}
      </div>
      <div>
        <s:label for="code" /><br />
        <s:text name="job.code" id="code" size="103" />
      </div>
      <div>
        <s:label for="job.discipline" /><br />
        <s:select name="disciplineId" id="job.discipline" value="${actionBean.job.disciplineId}">
          <s:option value="">-----</s:option>
          <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="displayOrder" />
        </s:select> 
      </div>
      <div>
        <s:label for="name" /><br />
        <s:text name="job.name" id="name" class="required" size="103" />
      </div>
      <div>
        <s:label for="location" /><br />
        <s:text name="job.location" id="location" class="required" size="103" />
      </div>
      <div>
        <s:label for="startDate" /><br />
        <s:text name="job.startDate" id="startDate" class="required" size="103" />
      </div>
      <div>
        <s:label for="endDate" /><br />
        <s:text name="job.endDate" id="endDate" class="required" size="103" />
      </div>
      <div>
        <s:label for="description" /><br />
        <s:textarea name="job.description" id="description" cols="100" rows="6" />
      </div>
      <div>
        <s:label for="displayOrder" /><br />
        <s:text name="job.displayOrder" id="displayOrder" class="required" size="3" />
      </div>
      <div>
        <s:submit name="save" value="Save" />
        <s:submit name="cancel" value="Cancel" />
        <c:if test="${actionBean.job.id!=null}">
          <s:submit name="delete" value="Delete" />
        </c:if>
      </div>
      <s:hidden name="job.id" />
      <s:hidden name="job.numberOfChanges" />
    </s:form>
  </s:layout-component>
</s:layout-render>
    