<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.DomicileActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          Id
        </td>
        <td>
          ${actionBean.domicile.id} 
        </td>
      </tr>
      <tr>
        <td>
          Domicile
        </td>
        <td>
          <s:text name="domicile.name" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          Display Order
        </td>
        <td>
          <s:text name="domicile.displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.domicile.id!=null}">
          <s:submit name="delete" value="Delete"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="domicile.id" />
    <s:hidden name="domicile.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    