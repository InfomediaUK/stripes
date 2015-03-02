<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/layoutMain.jsp" 
  title="Registration" 
  metaDescription="register"
  metaKeywords="job">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.RegistrationActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
         <s:label for="prospect.title" />
        </td>
        <td>
         <s:select name="title" id="prospect.title" class="wide">
           <s:options-collection collection="${actionBean.titleList}" value="name" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
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
         <s:label for="prospect.contactTelephone" /> 
        </td>
        <td>
         <s:text name="prospect.contactTelephone" id="prospect.contactTelephone" size="50" /> 
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
           <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.visa" />
        </td>
        <td>
         <s:select name="visaId" id="prospect.visa" class="wide">
           <s:options-collection collection="${actionBean.visaList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.domicile" />
        </td>
        <td>
         <s:select name="domicileId" id="prospect.domicile" class="wide">
           <s:options-collection collection="${actionBean.domicileList}" value="id" sort="displayOrder" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.dateAvailable" />
        </td>
        <td>
         <s:select name="availableDayNumber" id="prospect.dateAvailable" class="day" >
           <s:options-collection collection="${actionBean.dayNumberList}" value="id" sort="id" />
         </s:select>
         &nbsp; 
         <s:select name="availableMonth" class="month" >
           <s:options-collection collection="${actionBean.monthList}" value="id" sort="id" />
         </s:select>
         &nbsp; 
         <s:select name="availableYear" class="year" value="${actionBean.availableYear}" >
           <s:options-collection collection="${actionBean.yearList}" value="name" sort="id"  />
         </s:select>
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
