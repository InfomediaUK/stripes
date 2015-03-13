<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<jsp:useBean id="date" class="java.util.Date" />
<fieldset>
  <div id="email">
      <p>Email: info@pjlocums.co.uk</p>
  </div>
  <div id="ukcontact">
    <p>(In the UK)</p>
    <p>Freephone: 0800 032 0454</p>
    <p>Fax: 020 8874 7222</p>
  </div>
  <div id="internationalcontact">
    <p>(International)</p>
    <p>Tel: + 44 20 8874 6111</p>
    <p>Fax: + 44 20 8874 7222</p>
  </div>
  <div id="iso9001">
    <img src="${contextPath}/images/iso9001.gif" alt="iso 9001" width="155" height="49" />
  </div>
</fieldset>
<p>&copy; <fmt:formatDate value="${date}" pattern="yyyy" /> P J Locums</p>