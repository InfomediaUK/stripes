<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/mobile/layout/layoutMain.jsp" 
  title="Registration" 
  metaDescription="register"
  metaKeywords="job">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.RegistrationFormActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="prospect.firstName" /><br />
          <s:text name="prospect.firstName" id="prospect.firstName" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospect.lastName" /><br />
          <s:text name="prospect.lastName" id="prospect.lastName" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.gender" /><br />
         <c:forEach var="gender" items="<%= net.infomediauk.xml.jaxb.model.Gender.values() %>">
            <s:radio name="gender" value="${gender}" id="${gender.description}"/> <s:label for="${gender.description}" />&nbsp;
          </c:forEach>
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.mobileTelephone" /><br /> 
         <input type="tel" name="prospect.mobileTelephone" id="prospect.mobileTelephone" required="required" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.email" /><br /> 
         <input type="email" name="prospect.email" id="prospect.email" placeholder="name@example.com" required="required" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.profession" /><br />
         <s:text name="prospect.profession" id="prospect.profession" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.discipline" /><br />
         <s:select name="disciplineId" id="prospect.discipline" class="wide">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.visa" /><br />
         <s:select name="visaId" id="prospect.visa" class="wide">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.visaList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.domicile" /><br />
         <s:select name="domicileId" id="prospect.domicile" class="wide">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.domicileList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.dateAvailable" /><br />
         <s:select name="availableDayNumber" id="prospect.dateAvailable" class="day" >
           <s:options-collection collection="${actionBean.dayNumberList}" value="id" sort="id" />
         </s:select>
         &nbsp; 
         <s:select name="availableMonth" class="month" >
           <s:options-collection collection="${actionBean.monthList}" value="id" sort="id" />
         </s:select>
         &nbsp; 
         <s:select name="availableYear" class="year" value="${actionBean.availableYear}" >
           <s:options-collection collection="${actionBean.yearList}" value="id" sort="id"  />
         </s:select>
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.lengthOfStay" /><br />
         <s:select name="lengthOfStayId" id="prospect.lengthOfStay" class="wide">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.lengthOfStayList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="fileBean" /><br />
         <s:file name="fileBean" id="fileBean" /> 
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Submit Your Details"  />
        </td>
      </tr>
    </table>
  </s:form>
  </s:layout-component>
</s:layout-render>
