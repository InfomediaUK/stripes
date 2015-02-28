<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/layoutMain.jsp" 
  title="Registration" 
  metaDescription="register"
  metaKeywords="job">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.RegisterActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="prospect.fullName" />
        </td>
        <td>
          <s:text name="prospect.fullName" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.contactTelephone" /> 
        </td>
        <td>
         <s:text name="prospect.contactTelephone" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.email" /> 
        </td>
        <td>
         <s:text name="prospect.email" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.profession" />
        </td>
        <td>
         <s:text name="prospect.profession" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.discipline" />
        </td>
        <td>
         <s:select name="discipline" >
           <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="name" />
         </s:select> 
        </td>
      </tr>
      <tr>
        <td>
         <s:label for="prospect.visa" />
        </td>
        <td>
         <s:select name="visa" >
           <s:options-collection collection="${actionBean.visaList}" value="id" sort="name" />
         </s:select> 
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
