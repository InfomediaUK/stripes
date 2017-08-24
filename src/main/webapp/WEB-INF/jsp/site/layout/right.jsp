<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="box">
  <div class="holder">
    <div class="frame">
      <div class="content">
        <h3>Looking For Work?</h3>
        <s:form beanclass="net.infomediauk.stripes.action.RegisterFormActionBean"
          class="register-form">
          <s:submit name="start" value="Register With Us" class="submit" />
        </s:form>
      </div>
    </div>
  </div>
</div>
<c:if test="${not empty actionBean.jobList}">
<div class="box">
  <div class="holder">
    <div class="frame">
      <div class="content">
        Jobs For You!
        <c:forEach var="job" items="${actionBean.jobList}" >
          <p>${job.code}&nbsp;${job.name}
        </c:forEach>
      </div>
    </div>
  </div>
</div>
</c:if>
