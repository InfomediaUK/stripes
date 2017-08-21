<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-definition>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>${title}</title>
  <meta name="description" content="${metaDescription}" />
  <meta name="keywords" content="${metaKeywords}" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="${contextPath}/css/mobile.css">
</head>
<body>
  <div id="pageContainer">
    <header id="pageHeader">
      <div id="logo">
        <s:link beanclass="net.infomediauk.stripes.action.HomeActionBean" event="view">
          <img src="${contextPath}/images/logo.gif" alt="pj locums" width="141" height="72" title="pj locums" />
        </s:link>
      </div>
    </header>
    <div id="contentContainer" class="clearfix">
      <section id="pageSection">
        <header class="sectionHeader">
          <fieldset>
            <ul id="nav">
              <li class="active-home">
                <a href="${contextPath}/" title="Home"><span>Home</span></a>
              </li>
              <li>
                <s:link beanclass="net.infomediauk.stripes.action.FaqsActionBean" title="Frequently Asked Questions">
                  <span>FAQs</span>
                </s:link>
              </li>
              <li class="active-cpd">
                <s:link beanclass="net.infomediauk.stripes.action.CpdActionBean" title="Continued Professional Development">
                  <span>CPD</span>
                </s:link>
              </li>
              <li class="active-doc">
                <s:link beanclass="net.infomediauk.stripes.action.DocumentationActionBean" title="Documentation">
                  <span>Documentation</span>
                </s:link>
              </li>
              <li class="active-about">
                <s:link beanclass="net.infomediauk.stripes.action.AboutUsActionBean" title="About Us">
                  <span>About Us</span>
                </s:link>
              </li>
              <li class="active-contact">
                <s:link beanclass="net.infomediauk.stripes.action.ContactUsActionBean" title="Contact Information">
                  <span>Contact Us</span>
                </s:link>
              </li>
            </ul>
          </fieldset>
            <s:form beanclass="net.infomediauk.stripes.action.RegisterFormActionBean" class="register-form">
              <h3>Looking For Work?</h3>
              <s:submit name="start" value="Register With Us" class="submit" />
            </s:form>
        </header>
        <article class="sectionArticle">
          <s:layout-component name="body"/>
        </article>
        <footer class="sectionFooter">
          <s:layout-component name="left">
            <jsp:include page="/WEB-INF/jsp/mobile/layout/menu.jsp"/>
          </s:layout-component>
        </footer>
      </section>
    </div>
    <footer id="pageFooter">
      <div id="footer" style="clear:both; padding-top:10px;">
        <s:layout-component name="footer">
          <jsp:include page="/WEB-INF/jsp/mobile/layout/footer.jsp"/>
        </s:layout-component>
      </div>
    </footer>
  </div>
</body>
</html>
</s:layout-definition>
