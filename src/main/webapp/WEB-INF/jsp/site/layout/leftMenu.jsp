<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="box">
  <div class="holder">
    <div class="frame"  style="background: #fff">
      <div class="content">
        <h3>Sectors</h3>
        <h4>AHP/HSS Sector</h4>
        <ul class="menu menu2">
          <li id="dietitians">
            <s:link beanclass="net.infomediauk.stripes.action.DietitiansActionBean" event="view">
              Dietitians
            </s:link>
          </li>
          <li id="pharmacy">
            <s:link beanclass="net.infomediauk.stripes.action.PharmacyActionBean" event="view">
              Pharmacy
            </s:link>
          </li>
          <li id="physiotherapy">
            <s:link beanclass="net.infomediauk.stripes.action.PhysiotherapyActionBean" event="view">
              Physiotherapy
            </s:link>
          </li>
          <li id="podiatry">
            <s:link beanclass="net.infomediauk.stripes.action.PodiatryActionBean" event="view">
              Podiatry
            </s:link>
          </li>
          <li id="radiography">
            <s:link beanclass="net.infomediauk.stripes.action.RadiographyActionBean" event="view">
              Radiography
            </s:link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
  