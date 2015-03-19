<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/mobile/layout/layoutMain.jsp" 
  title="Registration" 
  metaDescription="register"
  metaKeywords="job">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.RegistrationFormActionBean">
    <s:errors/>
    <ul>
      <li>
        <s:label for="prospect.firstName" />
      </li>
      <li>
        <input type="text" name="prospect.firstName" id="prospect.firstName" required="required" /> 
      </li>
      <li>
        <s:label for="prospect.lastName" />
      </li>
      <li>
        <s:text name="prospect.lastName" id="prospect.lastName" /> 
      </li>
      <li>
        <s:label for="prospect.gender" />
      </li>
      <li>
        <c:forEach var="gender" items="<%= net.infomediauk.xml.jaxb.model.Gender.values() %>">
          <s:radio name="gender" value="${gender}" id="${gender.name}"/> <s:label for="${gender.name}" />&nbsp;
        </c:forEach>
      </li>
      <li>
        <s:label for="prospect.mobileTelephone" /> 
      </li>
      <li>
        <input type="tel" name="prospect.mobileTelephone" id="prospect.mobileTelephone" required="required" /> 
      </li>
      <li>
        <s:label for="prospect.email" /> 
      </li>
      <li>
        <input type="email" name="prospect.email" id="prospect.email" placeholder="name@example.com" required="required" /> 
      </li>
      <li>
        <s:label for="prospect.profession" />
      </li>
      <li>
        <s:text name="prospect.profession" id="prospect.profession" /> 
      </li>
      <li>
        <s:label for="prospect.discipline" />
      </li>
      <li>
        <s:select name="disciplineId" id="prospect.discipline" >
          <s:option value="">-----</s:option>
          <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="displayOrder" />
        </s:select> 
      </li>
      <li>
        <s:label for="prospect.passport" />
      </li>
      <li>
        <s:select name="passportId" id="prospect.passport" >
          <s:option value="">-----</s:option>
          <s:options-collection collection="${actionBean.passportList}" value="id" sort="displayOrder" />
        </s:select> 
      </li>
      <li>
        <s:label for="prospect.visa" />
      </li>
      <li>
        <s:select name="visaId" id="prospect.visa" >
          <s:option value="">-----</s:option>
          <s:options-collection collection="${actionBean.visaList}" value="id" sort="displayOrder" />
        </s:select> 
      </li>
      <li>
        <s:label for="prospect.dateAvailable" />
      </li>
      <li>
        <s:select name="availableDayNumber" id="prospect.dateAvailable" >
          <s:options-collection collection="${actionBean.dayNumberList}" value="id" sort="id" />
        </s:select>
        &nbsp; 
        <s:select name="availableMonth" >
          <s:options-collection collection="${actionBean.monthList}" value="id" sort="id" />
        </s:select>
        &nbsp; 
        <s:select name="availableYear" value="${actionBean.availableYear}" >
          <s:options-collection collection="${actionBean.yearList}" value="id" sort="id"  />
        </s:select>
      </li>
      <li>
        <s:label for="prospect.lengthOfStay" />
      </li>
      <li>
        <s:select name="lengthOfStayId" id="prospect.lengthOfStay">
          <s:option value="">-----</s:option>
          <s:options-collection collection="${actionBean.lengthOfStayList}" value="id" sort="displayOrder" />
        </s:select> 
      </li>
      <li>
        <s:label for="fileBean" />
      </li>
      <li>
        <s:file name="fileBean" id="fileBean" /> 
      </li>
      <li>
         <s:submit name="save" value="Submit Your Details"  />
      </li>
    </ul>
  </s:form>
  </s:layout-component>
</s:layout-render>
