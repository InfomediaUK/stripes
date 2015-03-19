<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="agency">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.AgencyActionBean">
    <s:errors/><s:messages/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="id" />
        </td>
        <td>
        <c:if test="${actionBean.agency.id==null}">
          <s:text name="agency.id" id="id" class="required" size="4" />
        </c:if>  
        <c:if test="${actionBean.agency.id!=null}">
           ${actionBean.agency.id}
           <s:hidden name="agency.id" />
        </c:if>  
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="code" />
        </td>
        <td>
          <s:text name="agency.code" id="code" size="6" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="agency" />
        </td>
        <td>
          <s:text name="agency.name" id="agency" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="displayOrder" />
        </td>
        <td>
          <s:text name="agency.displayOrder" id="displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.agency.id!=null}">
          <s:submit name="delete" value="Delete"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="agency.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    