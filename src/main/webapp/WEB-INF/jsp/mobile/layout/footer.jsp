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
    <img src="images/iso900.jpg" alt="ISO 900" width="194" height="109" />
  </div>
</fieldset>
<p>
  &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> P J Locums
  <s:link beanclass="net.infomediauk.stripes.action.ToggleSiteActionBean" event="view">
    <s:param name="currentActionBeanClassName" value="${actionBean.currentActionBeanClassName}"/>
    Desk Top Site
  </s:link>
</p>