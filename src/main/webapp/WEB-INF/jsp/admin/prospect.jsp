<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="prospect">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.ProspectActionBean">
    <s:hidden name="prospect.email" />
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          Upload Base URL
        </td>
        <td>
          ${actionBean.prospectUploadBaseUrl} 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospectAdminForm.uploadToAgency" />
        </td>
        <td>
          <s:select name="agencyId" id="agency" class="wide">
            <s:options-collection collection="${actionBean.agencyList}" value="id" sort="displayOrder" />
          </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          File
        </td>
        <td>
          ${actionBean.prospectFileName} 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.firstName" />
        </td>
        <td>
          <s:text name="prospect.firstName" id="prospect.firstName" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.lastName" />
        </td>
        <td>
          <s:text name="prospect.lastName" id="prospect.lastName" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.gender" />
        </td>
        <td>
          ${actionBean.prospect.gender.name}
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.mobileTelephone" />
        </td>
        <td>
          <s:text name="prospect.mobileTelephone" id="prospect.mobileTelephone" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.email" />
        </td>
        <td>
          ${actionBean.prospect.email} <%-- Note. You must NOT edit the email address as it is used as the prospect's filename.  --%>
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.profession" />
        </td>
        <td>
          <s:text name="prospect.profession" id="prospect.profession" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.availableForWork" />
        </td>
        <td>
          <s:text name="prospect.availableForWork" id="prospect.availableForWork" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.discipline" />
        </td>
        <td>
          <s:select name="disciplineId" id="prospect.discipline" value="${actionBean.prospect.disciplineId}" class="wide">
            <s:option value="">-----</s:option>
            <s:options-collection collection="${actionBean.disciplineList}" value="id" label="name" sort="displayOrder" />
          </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.idDocument" />
        </td>
        <td>
          <s:select name="idDocumentId" id="prospect.idDocument" value="${actionBean.prospect.idDocumentId}" class="wide">
            <s:option value="">-----</s:option>
            <s:options-collection collection="${actionBean.idDocumentList}" value="id" label="name" sort="displayOrder" />
          </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.visa" />
        </td>
        <td>
          <s:select name="visaId" id="prospect.visa" value="${actionBean.prospect.visaId}" class="wide">
            <s:option value="">-----</s:option>
            <s:options-collection collection="${actionBean.visaList}" value="id" label="name" sort="displayOrder" />
          </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.lengthOfStay" />
        </td>
        <td>
          <s:select name="lengthOfStayId" id="prospect.lengthOfStay" value="${actionBean.prospect.lengthOfStayId}" class="wide">
            <s:options-collection collection="${actionBean.lengthOfStayList}" value="id" label="name" sort="displayOrder" />
          </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospectAdminForm.cvAttached" />
        </td>
        <td>
          ${actionBean.prospect.hasDocumentFile}
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
          <s:submit name="delete" value="Delete"  />
          <s:submit name="revert" value="Revert"  />
          <s:submit name="sendMultiPartToMmj" value="Send to MMJ"  />
        </td>
      </tr>
    </table>
    <s:hidden name="prospectFileName" />
    <s:hidden name="prospect.documentFileName" />
    <s:hidden name="prospect.gender" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    