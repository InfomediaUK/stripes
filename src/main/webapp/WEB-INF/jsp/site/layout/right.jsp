<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<fieldset>
  <h3><fmt:message key="registerWithUs" /></h3>
  <s:form beanclass="net.infomediauk.stripes.action.RegisterFormActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="prospectShort.fullName"/><br />
          <s:text name="prospectShort.fullName" id="prospectShort.fullName" size="23" /> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospectShort.mobileTelephone"/><br />
          <s:text name="prospectShort.mobileTelephone" id="prospectShort.mobileTelephone"  size="23" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospectShort.email"/><br />
          <sd:text name="prospectShort.email" id="prospectShort.email" placeholder="name@example.com" size="23" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="prospectShort.profession"/><br />
          <sd:text name="prospectShort.profession" id="prospectShort.profession" placeholder="Your Current Job" size="23" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="start" value="Register" />
        </td>
      </tr>
    </table>
  </s:form>
</fieldset>