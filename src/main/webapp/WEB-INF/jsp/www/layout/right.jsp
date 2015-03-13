<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<fieldset>
  <h3><fmt:message key="registerWithUs" /></h3>
  <s:form beanclass="net.infomediauk.stripes.action.RegisterFormActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
          <label for="fullName">Full Name</label><br />
          <s:text name="prospectShort.fullName" value ="" size="23" /> 
        </td>
      </tr>
      <tr>
        <td>
          Contact Tel. (incl code)<br />
          <s:text name="prospectShort.contactTelephone"  size="23" />
        </td>
      </tr>
      <tr>
        <td>
          Email Address<br />
          <s:text name="prospectShort.email"  size="23" />
        </td>
      </tr>
      <tr>
        <td>
          Profession<br />
          <sd:text name="prospectShort.profession" placeholder="Your Job" size="23" />
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