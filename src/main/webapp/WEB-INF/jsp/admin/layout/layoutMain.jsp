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
      <link rel="stylesheet" type="text/css" href="${contextPath}/css/site.css">
    </head>
    <body>
      <table>
        <tr>
          <td>
            <s:link beanclass="net.infomediauk.stripes.action.admin.DisciplineListActionBean" event="view">
              Disciplines
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.PassportListActionBean" event="view">
              Passports
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.VisaListActionBean" event="view">
              Visas
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.DomicileListActionBean" event="view">
              Domiciles
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.TitleListActionBean" event="view">
              Titles
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.LengthOfStayListActionBean" event="view">
              Lengths of Stay
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.ProspectListActionBean" event="view">
              Prospects
            </s:link>
            &nbsp;
            <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageListActionBean" event="view">
              HTML Pages
            </s:link>
          </td>
        </tr>
        <tr>
          <td>
            <s:layout-component name="body"/>
          </td>
        </tr>
      </table>
    </body>
  </html>
</s:layout-definition>
