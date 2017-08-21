<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<jsp:useBean id="date" class="java.util.Date" />
<fieldset>
  <div id="iso9001">
    <img src="images/iso900.jpg" alt="ISO 900" />
  </div>
  <br />
  <div id="framework-accreditation">
    <img src="images/Framework-accreditation.jpg" alt="Framework Accreditation" />
  </div>
  <div class="contact">
    <p>3a Archway Mews | Putney Bridge | London | SW15 2PE</p>
    <p>VAT No: 792 2329 17 | Company Registration Number: 4371384</p>
  </div>
</fieldset>
<p>
  &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> P J Locums
  <s:link beanclass="net.infomediauk.stripes.action.ToggleSiteActionBean" event="view">
    <s:param name="currentActionBeanClassName" value="${actionBean.currentActionBeanClassName}"/>
    Desk Top Site
  </s:link>
</p>