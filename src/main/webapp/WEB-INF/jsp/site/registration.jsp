<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/site/layout/layoutMain.jsp" 
  title="Registration" 
  metaDescription="register"
  metaKeywords="job" >
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.RegistrationFormActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="prospect.firstName" />
        </td>
        <td>
          <s:text name="prospect.firstName" id="prospect.firstName" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.lastName" />
        </td>
        <td>
          <s:text name="prospect.lastName" id="prospect.lastName" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.gender" />
        </td>
        <td>
          <c:forEach var="gender" items="<%=net.infomediauk.xml.jaxb.model.Gender.values()%>">
            <s:radio name="gender" value="${gender}" id="${gender.name}"/> <s:label for="${gender.name}" />&nbsp;
          </c:forEach>
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.mobileTelephone" /> 
        </td>
        <td>
         <s:text name="prospect.mobileTelephone" id="prospect.mobileTelephone" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.email" /> 
        </td>
        <td>
         <s:text name="prospect.email" id="prospect.email" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.profession" />
        </td>
        <td>
         <s:text name="prospect.profession" id="prospect.profession" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.discipline" />
        </td>
        <td>
         <s:select name="disciplineId" id="prospect.discipline" class="wide">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.passport" />
        </td>
        <td>
         <s:select name="passportId" id="prospect.passport" class="wide">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.passportList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.visa" />
        </td>
        <td>
         <s:select name="visaId" id="prospect.visa" class="wide">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.visaList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.availableForWork" />
        </td>
        <td>
          <sd:text name="prospect.availableForWork" id="prospect.availableForWork" placeholder="DD/MM/YYYY" maxlength="10" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.lengthOfStay" />
        </td>
        <td>
          <s:select name="lengthOfStayId" id="prospect.lengthOfStay" class="wide">
            <s:options-collection collection="${actionBean.lengthOfStayList}" value="id" sort="displayOrder" />
          </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="fileBean" />
        </td>
        <td>
          <s:file name="fileBean" id="fileBean" /> 
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
