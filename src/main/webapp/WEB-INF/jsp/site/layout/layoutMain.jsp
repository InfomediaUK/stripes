<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-definition>
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  <html>
    <head>
      <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
      <title>${title}</title>
      <meta name="description" content="${metaDescription}" />
      <meta name="keywords" content="${metaKeywords}" />
      <link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
    </head>
    <body>
      <div id="container" style="width:980px; margin:0 auto; position:relative;">
        <div id="main_right" style="float:right; width:810px; margin-top:160px;">
          <div id="main_content" style="float:left; width:600px; margin-right:10px;">
            <s:layout-component name="body"/>
          </div>
          <div id="right_sidebar" style="float:right; width:200px;">
            <s:layout-component name="right">
            <jsp:include page="/WEB-INF/jsp/site/layout/right.jsp"/>
            </s:layout-component>
          </div>
        </div>
        <div id="left_sidebar" style="float:left; width:160px; margin-right:10px; margin-top:160px;">
          <s:layout-component name="left">
            <jsp:include page="/WEB-INF/jsp/site/layout/leftMenu.jsp"/>
          </s:layout-component>
        </div>
        <div id="header" style="height:120px; margin:0; padding:0; position:absolute; top:0; left:0; width:980px;">
          <div id="logo">
            <s:link beanclass="net.infomediauk.stripes.action.HomeActionBean" event="view">
                 <img src="${contextPath}/images/logo.gif" alt="pj locums" width="141" height="72" title="pj locums" />
            </s:link>
          </div>
          <div>
            <s:link beanclass="net.infomediauk.stripes.action.FaqsActionBean" event="view">
              FAQs
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.DocumentationActionBean" event="view">
              Documentation
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.AboutUsActionBean" event="view">
              About Us
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.ContactUsActionBean" event="view">
              Contact Us
            </s:link>
          </div>
          <div id="tagline"><h1><fmt:message key="tagLine" /></h1></div>
        </div>
        <div id="footer" style="clear:both; padding-top:10px;">
          <s:layout-component name="footer">
            <jsp:include page="/WEB-INF/jsp/site/layout/footer.jsp"/>
          </s:layout-component>
        </div>
      </div>
    </body>
  </html>
</s:layout-definition>
