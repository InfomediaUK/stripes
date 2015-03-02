<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/admin/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.VisaActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          Id
        </td>
        <td>
          ${actionBean.visa.id} 
        </td>
      </tr>
      <tr>
        <td>
          Visa
        </td>
        <td>
          <s:text name="visa.name" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          Display Order
        </td>
        <td>
          <s:text name="visa.displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.visa.id!=null}">
          <s:submit name="delete" value="Delete"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="visa.id" />
    <s:hidden name="visa.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    