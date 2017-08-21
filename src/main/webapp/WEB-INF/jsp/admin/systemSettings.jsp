<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"  pageHeading="systemSettings">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.SystemSettingsActionBean">
    <s:errors/><s:messages/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="systemSettings.prospectUploadBaseUrl" />
        </td>
        <td>
          <s:text name="systemSettings.prospectUploadBaseUrl" id="systemSettings.prospectUploadBaseUrl" class="required" size="50" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="systemSettings.matchMyJobRestBaseUrl" />
        </td>
        <td>
          <s:text name="systemSettings.matchMyJobRestBaseUrl" id="systemSettings.matchMyJobRestBaseUrl" class="required" size="50" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="systemSettings.emailFromAddress" />
        </td>
        <td>
          <s:text name="systemSettings.emailFromAddress" id="systemSettings.emailFromAddress" size="50" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="systemSettings.emailProperties" />
        </td>
        <td>
          <s:text name="systemSettings.emailProperties" id="systemSettings.emailProperties" size="50" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="systemSettings.emailUserName" />
        </td>
        <td>
          <s:text name="systemSettings.emailUserName" id="systemSettings.emailUserName" size="50" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="systemSettings.emailPassword" />
        </td>
        <td>
          <s:text name="systemSettings.emailPassword" id="systemSettings.emailPassword" size="50" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
        </td>
      </tr>
    </table>
  </s:form>
  </s:layout-component>
</s:layout-render>
    