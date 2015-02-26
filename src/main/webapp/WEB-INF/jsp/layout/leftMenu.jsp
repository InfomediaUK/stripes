<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<ul>
  <li id="dietitians">
    <s:link beanclass="net.infomediauk.stripes.action.DietitiansActionBean" event="view">
      Dietitians
    </s:link>
  </li>
  <li id="pharmacists">
    <s:link beanclass="net.infomediauk.stripes.action.PharmacistsActionBean" event="view">
      Pharmacists
    </s:link>
  </li>
  <li id="podiatrists">
    <s:link beanclass="net.infomediauk.stripes.action.PodiatristsActionBean" event="view">
      Podiatrists
    </s:link>
  </li>
  <li id="radiographer">
    <s:link beanclass="net.infomediauk.stripes.action.RadiographersActionBean" event="view">
      Radiographer
    </s:link>
  </li>
</ul>
