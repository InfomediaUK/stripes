<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-definition>
  <!DOCTYPE html>
  <html>
    <head>
      <meta charset="UTF-8">
      <title>${title}</title>
      <meta name="description" content="${metaDescription}" />
      <meta name="keywords" content="${metaKeywords}" />
      <link media="all" rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
      <link media="all" rel="stylesheet" type="text/css" href="${contextPath}/css/fonts.css" />
      <script type="text/javascript" src="${contextPath}/js/main.js" ></script>
    </head>
    <body>
      <div id="container" style="width:980px; margin:0 auto; position:relative;">
        <div id="main_right" style="float:right; width:735px; margin-top:180px;">
          <div id="main_content" style="float:left; width:476px; margin-right:10px;">
            <s:layout-component name="body"/>
          </div>
          <div id="right_sidebar">
            <s:layout-component name="right">
              <jsp:include page="/WEB-INF/jsp/site/layout/right.jsp"/>
            </s:layout-component>
          </div>
        </div>
        <div id="sidebar" style="float:left; width:160px; margin-right:10px; margin-top:180px;">
          <s:layout-component name="left">
            <jsp:include page="/WEB-INF/jsp/site/layout/leftMenu.jsp"/>
          </s:layout-component>
        </div>
        <div id="wrapper" style="height:100px; margin:0; padding:0; position:absolute; top:0; left:0; width:953px;">
          <div id="header">
            <strong class="logo">
              <s:link beanclass="net.infomediauk.stripes.action.HomeActionBean" event="view">
                <img src="${contextPath}/images/logo.gif" alt="pj locums" width="141" height="72" title="PJ Locums" />
              </s:link>
            </strong>
            <span class="login"><a href="http://www.matchmyjob.co.uk/mmj/zap">Applicant Login</a></span>
            <h1><fmt:message key="tagLine" /></h1>
          </div>
          <div class="nav-block">
            <ul id="nav">
              <li class="active-home">
                <s:link beanclass="net.infomediauk.stripes.action.HomeActionBean" event="view" title="Home">
                  <span>Home</span>
                </s:link>
              </li>
              <li>
                <s:link beanclass="net.infomediauk.stripes.action.FaqsActionBean" event="view" title="Frequently Asked Questions">
                  <span>FAQs</span>
                </s:link>
              </li>
              <li class="active-cpd">
                <s:link beanclass="net.infomediauk.stripes.action.CpdActionBean" event="view" title="Continued Professional Development">
                  <span>CPD</span>
                </s:link>
              </li>
              <li class="active-doc">
                <s:link beanclass="net.infomediauk.stripes.action.DocumentationActionBean" event="view" title="Documentation">
                  <span>Documentation</span>
                </s:link>
              </li>
              <li class="active-about">
                <s:link beanclass="net.infomediauk.stripes.action.AboutUsActionBean" event="view" title="About Us">
                  <span>About Us</span>
                </s:link>
              </li>
              <li class="active-contact">
                <s:link beanclass="net.infomediauk.stripes.action.ContactUsActionBean" event="view" title="Contact Information">
                  <span>Contact Us</span>
                </s:link>
              </li>
            </ul>
          </div>
        </div>
        <div id="footer" style="clear:both; padding-top:10px;">
          <s:layout-component name="footer">
            <jsp:include page="/WEB-INF/jsp/site/layout/footer.jsp"/>
          </s:layout-component>
        </div>
      </div>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <script src="${contextPath}/js/jquery.cycle2.min.js"></script>
      <script>
        $(".hide-fields").hide();
        $(function(){
          $('#firstname').focus(function(){
          $(".hide-fields").fadeIn('fast');
          });
        });
      </script>     
    </body>
  </html>
</s:layout-definition>
