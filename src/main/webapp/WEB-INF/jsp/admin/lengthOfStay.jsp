<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="lengthOfStay">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.LengthOfStayActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          Id
        </td>
        <td>
          ${actionBean.lengthOfStay.id} 
        </td>
      </tr>
      <tr>
        <td>
          LengthOfStay
        </td>
        <td>
          <s:text name="lengthOfStay.name" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          Display Order
        </td>
        <td>
          <s:text name="lengthOfStay.displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.lengthOfStay.id!=null}">
          <s:submit name="delete" value="Delete"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="lengthOfStay.id" />
    <s:hidden name="lengthOfStay.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    