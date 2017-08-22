<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@ taglib prefix="shtml5" uri="/stripes-html5"%>

<s:layout-render name="/WEB-INF/jsp/site/layout/layoutMain.jsp" 
  title="Registration" 
  metaDescription="register"
  metaKeywords="job" >
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.RegistrationFormActionBean" class="register-form">
    <s:errors globalErrorsOnly="true"/>
    <ul>
      <li>
          <s:errors field="prospect.firstName"/> 
          <s:label for="prospect.firstName" />
          <shtml5:text name="prospect.firstName" id="prospect.firstName" />
      </li>
      <li>
          <s:errors field="prospect.lastName"/> 
          <s:label for="prospect.lastName" />
          <shtml5:text name="prospect.lastName" id="prospect.lastName" /> 
      </li>
      <li>
          <s:errors field="gender"/> 
          <s:label for="gender" />
          <table>
            <tr>
            <c:forEach var="gender" items="<%=net.infomediauk.xml.jaxb.model.Gender.values()%>">
              <td>
                <s:label for="${gender.name}" /></td><td><s:radio name="gender" value="${gender}" id="${gender.name}"/></td><td>&nbsp;&nbsp;&nbsp;
              </td>
            </c:forEach>
            </tr>
          </table>
      </li>
      <li>
          <s:errors field="availableForWork"/> 
          <s:label for="availableForWork" /> 
          <shtml5:text name="availableForWork" id="availableForWork" class="datepicker" formatPattern="dd/MM/yyyy" pattern="\d{1,2}/\d{1,2}/\d{4}" placeholder="dd/mm/yyyy" /> 
      </li>
      <li>
         <s:errors field="prospect.profession"/> 
         <s:label for="prospect.profession" /> 
         <s:text name="prospect.profession" id="prospect.profession" /> 
      </li>
      <li>
         <s:errors field="prospect.email"/> 
         <s:label for="prospect.email" /> 
         <shtml5:text name="prospect.email" id="prospect.email" placeholder="eg. name@domain.com" /> 
      </li>
      <li>
          <s:errors field="prospect.mobileTelephone"/> 
         <s:label for="prospect.mobileTelephone" /> 
         <shtml5:text name="prospect.mobileTelephone" id="prospect.mobileTelephone" placeholder="eg. +442005556789" /> 
      </li>
      <li>
         <s:errors field="prospect.discipline"/> 
         <s:label for="prospect.discipline" /> 
         <s:select name="discipline" id="prospect.discipline">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="displayOrder" />
         </s:select> 
      </li>
      <li>
         <s:errors field="prospect.passport"/> 
         <s:label for="prospect.passport" /> 
         <s:select name="passport" id="prospect.passport">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.passportList}" value="id" sort="displayOrder" />
         </s:select> 
      </li>
      <li>
         <s:errors field="prospect.visa"/> 
         <s:label for="prospect.visa" /> 
         <s:select name="visa" id="prospect.visa">
           <s:option value="">-----</s:option>
           <s:options-collection collection="${actionBean.visaList}" value="id" sort="displayOrder" />
         </s:select> 
      </li>
      <li>
          <s:label for="prospect.lengthOfStay" /> 
          <s:select name="lengthOfStay" id="prospect.lengthOfStay" class="wide">
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
