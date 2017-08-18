<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/site/layout/layoutMain.jsp" 
  title="Registration" 
  metaDescription="register"
  metaKeywords="job" >
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.RegistrationFormActionBean" class="register-form">
    <s:errors/>
    <ul>
      <li>
          <s:label for="prospect.firstName" />
          <input type="text" name="prospect.firstName" id="prospect.firstName" required /> 
      </li>
      <li>
          <s:label for="prospect.lastName" />
          <input type="text" name="prospect.lastName" id="prospect.lastName" required /> 
      </li>
      <li>
          <s:label for="prospect.gender" />
          <table>
            <tr>
            <c:forEach var="gender" items="<%=net.infomediauk.xml.jaxb.model.Gender.values()%>">
              <td><s:label for="${gender.name}" /></td><td><s:radio name="gender" value="${gender}" id="${gender.name}"/></td><td>&nbsp;&nbsp;&nbsp;</td>
            </c:forEach>
            </tr>
          </table>
      </li>
      <li>
          <s:label for="prospect.availableForWork" /> 
          <input type="text" name="prospect.availableForWork" id="prospect.availableForWork" placeholder="DD/MM/YYYY" maxlength="10" required /> 
      </li>
      <li>
         <s:label for="prospect.mobileTelephone" /> 
         <input type="text" name="prospect.mobileTelephone" id="prospect.mobileTelephone" placeholder="Eg. +442005556789" /> 
      </li>
      <li>
         <s:label for="prospect.email" /> 
         <input type="email" name="prospect.email" id="prospect.email" placeholder="Eg. name@domain.com" required /> 
      </li>
      <li>
         <s:label for="prospect.profession" /> 
         <s:text name="prospect.profession" id="prospect.profession" size="50" /> 
      </li>
      <li>
         <s:label for="prospect.discipline" /> 
         <s:select name="disciplineId" id="prospect.discipline">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="displayOrder" />
         </s:select> 
      </li>
      <li>
         <s:label for="prospect.passport" /> 
         <s:select name="passportId" id="prospect.passport">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.passportList}" value="id" sort="displayOrder" />
         </s:select> 
      </li>
      <li>
         <s:label for="prospect.visa" /> 
         <s:select name="visaId" id="prospect.visa">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.visaList}" value="id" sort="displayOrder" />
         </s:select> 
      </li>
      <li>
          <s:label for="prospect.lengthOfStay" /> 
          <s:select name="lengthOfStayId" id="prospect.lengthOfStay" class="wide">
            <s:options-collection collection="${actionBean.lengthOfStayList}" value="id" sort="displayOrder" />
          </s:select> 
      </li>
      <li>
          <s:label for="fileBean" /> 
          <s:file name="fileBean" id="fileBean" /> 
      </li>
      <li>
          <s:submit name="save" value="Send Details" class="submit" />
      </li>
    </ul>
  </s:form>
  </s:layout-component>
</s:layout-render>
