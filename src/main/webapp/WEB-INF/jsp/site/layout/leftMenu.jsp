<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="box">
  <div class="holder">
    <div class="frame"  style="background: #fff">
      <div class="content">
        <h3>Sectors</h3>
        <h4>AHP/HSS Sector</h4>
        <ul class="menu menu2">
          <li id="dietetics">
            <s:link beanclass="net.infomediauk.stripes.action.DieteticsActionBean" event="view" class="button1" title="Dietitian">
              Dietetics
            </s:link>
          </li>
          <li id="occupationaltherapy">
            <s:link beanclass="net.infomediauk.stripes.action.OccupationalTherapyActionBean" event="view" class="button2" title="Occupational Therapist">
              Occupational Therapy
            </s:link>
          </li>
          <li id="pharmacy">
            <s:link beanclass="net.infomediauk.stripes.action.PharmacyActionBean" event="view" class="button3" title="Pharmacist">
              Pharmacy
            </s:link>
          </li>
          <li id="physiotherapy">
            <s:link beanclass="net.infomediauk.stripes.action.PhysiotherapyActionBean" event="view" class="button4" title="Physiotherapist">
              Physiotherapy
            </s:link>
          </li>
          <li id="podiatry">
            <s:link beanclass="net.infomediauk.stripes.action.PodiatryActionBean" event="view" class="button5" title="Podiatrist">
              Podiatry
            </s:link>
          </li>
          <li id="speechtherapy">
            <s:link beanclass="net.infomediauk.stripes.action.SpeechTherapyActionBean" event="view" class="button6" title="Speech Therapist">
              Speech Therapy
            </s:link>
          </li>
          <li id="radiography">
            <s:link beanclass="net.infomediauk.stripes.action.RadiographyActionBean" event="view" class="button7" title="Radiographer">
              Radiography
            </s:link>
          </li>
          <li id="odp">
            <s:link beanclass="net.infomediauk.stripes.action.OdpActionBean" event="view" class="button8" title="Operating Department Practioner">
              ODP
            </s:link>
          </li>
        </ul>
        <h4>Nursing Sector</h4>
        <ul class="menu">
          <li id="midwifery">
            <s:link beanclass="net.infomediauk.stripes.action.MidwiferyActionBean" event="view" title="Midwife">
              Midwifery
            </s:link>
          </li>
        </ul>
<%-- 
        <h4>Doctors Sector</h4>
        <ul class="menu">
          <li id="doctor">
            <s:link beanclass="net.infomediauk.stripes.action.MidwiferyActionBean" event="view" title="Doctor">
              SHO
            </s:link>
          </li>
        </ul>
--%>
      </div>
    </div>
  </div>
</div>
  