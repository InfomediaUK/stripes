<!--
 ! Excerpted from "Stripes: and Java Web Development is Fun Again",
 ! published by The Pragmatic Bookshelf.
 ! Copyrights apply to this code. It may not be used to create training material, 
 ! courses, books, articles, and the like. Contact us if you are in doubt.
 ! We make no guarantees that this code is fit for any purpose. 
 ! Visit http://www.pragmaticprogrammer.com/titles/fdstr for more book information.
-->
<%-- START:this --%>
<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-definition>
  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd">
  <html>
    <head>
      <title>${title}</title><!-- (1) -->
      <link rel="stylesheet" type="text/css"
        href="${contextPath}/css/style.css"><!-- (2) -->
    </head>
    <body>
      <div id="header">
        <span class="title">${title}</span><!-- (3) -->
      </div>
      <div id="body">
        <s:layout-component name="body"/><!-- (4) -->
      </div>
<%-- END:this --%>

      <!-- View source links just for convenience -->
      <div style="padding-left: 8px">
        Source:
        <s:link beanclass="stripesbook.action.ViewSourceActionBean">
          <s:param name="filename" value="<%= request.getRequestURI() %>"/>
          JSP
        </s:link> |
        <s:link beanclass="stripesbook.action.ViewSourceActionBean">
          <s:param name="filename" value="${actionBean.class.name}"/>
          Action Bean
        </s:link>
      </div>
<%-- START:this --%>
    </body>
  </html>
</s:layout-definition>
<%-- END:this --%>
