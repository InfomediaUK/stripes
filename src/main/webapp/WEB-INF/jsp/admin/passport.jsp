<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="passport">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.PassportActionBean">
    <s:errors/><s:messages/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="id" />
        </td>
        <td>
        <c:if test="${actionBean.passport.id==null}">
          <s:text name="passport.id" id="id" class="required" size="4" />
        </c:if>  
        <c:if test="${actionBean.passport.id!=null}">
           ${actionBean.passport.id}
           <s:hidden name="passport.id" />
        </c:if>  
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="code" />
        </td>
        <td>
          <s:text name="passport.code" id="code" size="6" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="passport" />
        </td>
        <td>
          <s:text name="passport.name" id="passport" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="displayOrder" />
        </td>
        <td>
          <s:text name="passport.displayOrder" id="displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.passport.id!=null}">
          <s:submit name="delete" value="Delete"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="passport.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    