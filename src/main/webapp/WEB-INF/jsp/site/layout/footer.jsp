<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<jsp:useBean id="date" class="java.util.Date" />
			<div class="footer-box">
				<div class="holder">
					<div class="frame">
						<ul class="partners">
							<li><a href="#"><img src="images/iso900.jpg" alt="ISO 900" width="194" height="109" /></a> ISO 9000 </li>
							<li><a href="#"><img src="images/Framework-accreditation.jpg" alt="Framework Accreditation" width="243" height="109" /></a>Framework accreditation</li>
                            <li><a href="#"><img src="images/REC-membership.jpg" alt="ISO 900" width="191" height="109" /></a> REC membership</li>
						</ul>
						<div class="contact">

							<p>3a Archway Mews | Putney Bridge | London | SW15 2PE<br />

Tel: 02088746111 | VAT No: 792 2329 17 | Company Reg No: 4371384
</p>
						</div>
					</div>
				</div>
			</div>

<ul class="footer-info">
  <li>&copy; <fmt:formatDate value="${date}" pattern="yyyy" /> P J Locums</li>
</ul>
<%-- 
  <s:link beanclass="net.infomediauk.stripes.action.ToggleSiteActionBean" event="view">
    <s:param name="currentActionBeanClassName" value="${actionBean.currentActionBeanClassName}"/>
    Mobile Site
  </s:link>
--%>