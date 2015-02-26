<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-definition>
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  <html>
    <head>
      <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
      <title>Admin</title>
      <meta name="description" content="Admin description" />
      <meta name="keywords" content="admin" />
      <link rel="stylesheet" type="text/css" href="${contextPath}/css/pjlocums.css">
    </head>
    <body>
      <table>
        <tr>
          <td>
            Menu Bar x
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
