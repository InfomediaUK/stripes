<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/admin/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.DisciplineActionBean" >
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          Id
        </td>
        <td>
          ${actionBean.discipline.id} 
        </td>
      </tr>
      <tr>
        <td>
          Discipline
        </td>
        <td>
          <s:text name="discipline.name" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.discipline.id!=null}">
          <s:submit name="delete" value="Delete"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="discipline.id" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    