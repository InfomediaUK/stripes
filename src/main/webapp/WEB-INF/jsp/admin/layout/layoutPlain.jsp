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
