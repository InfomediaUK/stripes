<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="visa">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.VisaActionBean">
    <s:errors/><s:messages/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="id" />
        </td>
        <td>
        <c:if test="${actionBean.visa.id==null}">
          <s:text name="visa.id" id="id" class="required" size="4" />
        </c:if>  
        <c:if test="${actionBean.visa.id!=null}">
           ${actionBean.visa.id}
           <s:hidden name="visa.id" />
        </c:if>  
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="code" />
        </td>
        <td>
          <s:text name="visa.code" id="code" size="6" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="visa" />
        </td>
        <td>
          <s:text name="visa.name" id="visa" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="displayOrder" />
        </td>
        <td>
          <s:text name="visa.displayOrder" id="displayOrder" class="required" size="3" />
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
    <s:hidden name="visa.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    