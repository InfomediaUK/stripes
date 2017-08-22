<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@ taglib prefix="shtml5" uri="/stripes-html5"%>

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
      <legend>Email Server</legend>
      <s:label for="systemSettings.emailServer" /> 
      <s:select name="systemSettings.emailServer" id="systemSettings.emailServer">
         <s:option value="${actionBean.emailHost}">${actionBean.emailHost}</s:option>
         <s:option value="${actionBean.exchangeServer}">${actionBean.exchangeServer}</s:option>
      </s:select> 
    </fieldset>
    <br />
    <fieldset>
      <legend>Email Host Settings</legend>
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
      <legend>MS Exchange Server Settings</legend>
      <div>
        <s:label for="systemSettings.exchangeVersion" /> (eg. Exchange2007_SP1, Exchange2010, Exchange2010_SP1, Exchange2010_SP2)<br />
        <shtml5:text name="systemSettings.exchangeVersion" id="systemSettings.exchangeVersion" size="103" list="exchangeVersionList" />
        <datalist id="exchangeVersionList">
          <option>Exchange2007_SP1</option>
          <option>Exchange2010</option>
          <option>Exchange2010_SP1</option>
          <option>Exchange2010_SP2</option>
        </datalist>
      </div>
      <div>
        <s:label for="systemSettings.exchangeUserName" /><br />
        <s:text name="systemSettings.exchangeUserName" id="systemSettings.exchangeUserName" size="103" />
      </div>
      <div>
        <s:label for="systemSettings.exchangePassword" /><br />
        <s:text name="systemSettings.exchangePassword" id="systemSettings.exchangePassword" size="103" />
      </div>
      <div>
        <s:label for="systemSettings.exchangeDomain" /><br />
        <s:text name="systemSettings.exchangeDomain" id="systemSettings.exchangeDomain" size="103" />
      </div>
    </fieldset>
    <div>
      <s:submit name="save" value="Save"  />
    </div>
  </s:form>
  </s:layout-component>
</s:layout-render>
    