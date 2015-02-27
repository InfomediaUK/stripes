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
          <label for="prospectShort.fullName">Full Name</label>
        </td>
        <td>
          <s:text name="prospectShort.fullName" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <label for="prospectShort.fullName">Contact Telephone</label> 
        </td>
        <td>
         <s:text name="prospectShort.contactTelephone" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <label for="prospectShort.email">Email</label> 
        </td>
        <td>
         <s:text name="prospectShort.email" size="50" /> 
        </td>
      </tr>
      <tr>
        <td>
         <label for="prospectShort.profession">Email</label> 
        </td>
        <td>
         <s:text name="prospectShort.profession" size="50" /> 
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
