<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"  pageHeading="systemSettings">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.SystemSettingsActionBean">
    <s:errors/><s:messages/>
    <fieldset>
      <legend>REST Settings</legend>
        <div>
          <s:label for="systemSettings.prospectUploadBaseUrl" /><br />
          <s:text name="systemSettings.prospectUploadBaseUrl" id="systemSettings.prospectUploadBaseUrl" class="required" size="103" />
        </div>
        <div>
          <s:label for="systemSettings.matchMyJobRestBaseUrl" /><br />
          <s:text name="systemSettings.matchMyJobRestBaseUrl" id="systemSettings.matchMyJobRestBaseUrl" class="required" size="103" />
        </div>
    </fieldset>
    <br />
    <fieldset>
      <legend>Email Settings</legend>
      <div>
        <s:label for="systemSettings.emailFromAddress" /><br />
        <s:text name="systemSettings.emailFromAddress" id="systemSettings.emailFromAddress" size="103" />
      </div>
      <div>
        <s:label for="systemSettings.emailProperties" /><br />
        <s:textarea name="systemSettings.emailProperties" id="systemSettings.emailProperties" cols="100" rows="6" />
      </div>
      <div>
        <s:label for="systemSettings.emailUserName" /><br />
        <s:text name="systemSettings.emailUserName" id="systemSettings.emailUserName" size="103" />
      </div>
      <div>
        <s:label for="systemSettings.emailPassword" /><br />
        <s:text name="systemSettings.emailPassword" id="systemSettings.emailPassword" size="103" />
      </div>
    </fieldset>
    <br />
    <fieldset>
      <legend>MS Exchange Settings</legend>
      <div>
        <s:label for="systemSettings.exchangeVersion" /><br />
        <s:text name="systemSettings.exchangeVersion" id="systemSettings.exchangeVersion" size="103" />
      </div>
      <div>
        <s:label for="systemSettings.exchangeUserName" /><br />
        <s:text name="systemSettings.exchangeUserName" id="systemSettings.exchangeUserName" size="103" />
      </div>
      <div>
        <s:label for="systemSettings.exchangePassword" /><br />
        <s:text name="systemSettings.exchangePassword" id="systemSettings.exchangePassword" size="103" />
      </div>
    </fieldset>
    <div>
      <s:submit name="save" value="Save"  />
    </div>
  </s:form>
  </s:layout-component>
</s:layout-render>
    