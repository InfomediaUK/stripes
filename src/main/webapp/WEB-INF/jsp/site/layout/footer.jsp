<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<jsp:useBean id="date" class="java.util.Date" />
<fieldset>
  <table width="900">
    <tr>
      <td>
        <img src="${contextPath}/images/iso9001.gif" alt="iso 9001" width="155" height="49" />
      </td>
      <td>
      </td>
      <td>
      </td>
      <td>
        <p>(In the UK)</p>
        <p>Freephone: 0800 032 0454</p>
        <p>Fax: 020 8874 7222</p>
        <p>Email: info@pjlocums.co.uk</p>
      </td>
      <td>
        <p>(International)</p>
        <p>Tel: + 44 20 8874 6111</p>
        <p>Fax: + 44 20 8874 7222</p>
      </td>
    </tr>
  </table>
</fieldset>
<p>
  &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> P J Locums
  <s:link beanclass="net.infomediauk.stripes.action.ToggleSiteActionBean" event="view">
    <s:param name="currentActionBeanClassName" value="${actionBean.currentActionBeanClassName}"/>
    Mobile Site
  </s:link>
</p>