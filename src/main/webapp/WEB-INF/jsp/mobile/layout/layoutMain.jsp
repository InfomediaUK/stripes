<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-definition>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>${title}</title>
  <meta name="description" content="${metaDescription}" />
  <meta name="keywords" content="${metaKeywords}" />
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
        </header>
        <article class="sectionArticle">
          <s:layout-component name="body"/>
        </article>
        <footer class="sectionFooter">
          <s:layout-component name="left">
            <jsp:include page="/WEB-INF/jsp/layout/leftMenu.jsp"/>
          </s:layout-component>
        </footer>
      </section>
    </div>
    <footer id="pageFooter">
      <div id="footer" style="clear:both; padding-top:10px;">
        <s:layout-component name="footer">
          <jsp:include page="/WEB-INF/jsp/layout/footer.jsp"/>
        </s:layout-component>
      </div>
    </footer>
  </div>
</body>
</html>
</s:layout-definition>
