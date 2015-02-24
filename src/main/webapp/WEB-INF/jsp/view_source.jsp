<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>View source - ${actionBean.filename}</title>
  </head>
  <body>
    <p><u>View source - ${actionBean.filename}</u></p>
    <pre>${fn:escapeXml(actionBean.source)}</pre>
  </body>
</html>
