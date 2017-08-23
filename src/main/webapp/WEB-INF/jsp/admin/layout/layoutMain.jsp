<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-definition>
  <!DOCTYPE html>
  <html>
    <head>
      <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
      <title>${title}</title>
      <meta name="description" content="${metaDescription}" />
      <meta name="keywords" content="${metaKeywords}" />
      <link rel="stylesheet" type="text/css" href="${contextPath}/css/admin.css">
    </head>
    <body>
      <table>
        <tr>
          <td>
            <s:link beanclass="net.infomediauk.stripes.action.admin.RoleListActionBean">
              Roles
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.UserListActionBean">
              Users
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.AgencyListActionBean">
              Agencies
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.DisciplineListActionBean">
              Disciplines
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.PassportListActionBean">
              Passports
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.VisaListActionBean">
              Visas
            </s:link>
<%-- 
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.DomicileListActionBean">
              Domiciles
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.TitleListActionBean">
              Titles
            </s:link>
--%>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.JobListActionBean">
              Jobs
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.LengthOfStayListActionBean">
              Lengths of Stay
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.ProspectListActionBean">
              Prospects
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageListActionBean">
              HTML Pages
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.SystemSettingsActionBean">
              Settings
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.LogoutActionBean">
              Logout
            </s:link>
          </td>
        </tr>
        <tr>
          <td>
            <h3><fmt:message key="${pageHeading}" /></h3>
          </td>
        </tr>
        <tr>
          <td>
            <s:layout-component name="body"/>
          </td>
        </tr>
      </table>
    </body>
  </html>
</s:layout-definition>
