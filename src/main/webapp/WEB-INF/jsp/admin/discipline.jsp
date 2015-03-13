<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.DisciplineActionBean">
    <s:errors/><s:messages/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="id" />
        </td>
        <td>
        <c:if test="${actionBean.discipline.id==null}">
          <s:text name="discipline.id" id="id" class="required" size="4" />
        </c:if>  
        <c:if test="${actionBean.discipline.id!=null}">
           ${actionBean.discipline.id}
           <s:hidden name="discipline.id" />
        </c:if>  
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="discipline" />
        </td>
        <td>
          <s:text name="discipline.name" id="discipline" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="displayOrder" />
        </td>
        <td>
          <s:text name="discipline.displayOrder" id="displayOrder" class="required" size="3" />
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
    <s:hidden name="discipline.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    