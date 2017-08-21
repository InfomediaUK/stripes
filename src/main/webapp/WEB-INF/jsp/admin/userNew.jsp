<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="user">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.UserNewActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
       <td>
          <s:label for="user.name" />
        </td>
        <td>
          <s:text name="user.name" id="user.name" class="required" />
        </td>
      </tr>
      <tr>
       <td>
          <s:label for="email" />
        </td>
        <td>
          <s:text name="user.email" id="email" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="password" />
        </td>
        <td>
          <s:password name="password" id="password" repopulate="true" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="confirmPassword" />
        </td>
        <td>
          <s:password name="confirmPassword" id="confirmPassword" repopulate="true" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="passwordHint" />
        </td>
        <td>
          <s:password name="passwordHint" id="passwordHint" repopulate="true" class="required" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="displayOrder" />
        </td>
        <td>
          <s:text name="user.displayOrder" id="displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        </td>
      </tr>
    </table>
  </s:form>
  </s:layout-component>
</s:layout-render>
    