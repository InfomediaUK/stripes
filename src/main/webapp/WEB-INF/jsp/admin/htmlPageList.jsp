<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/admin/layoutMain.jsp" >
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean" event="create">
      <s:param name="htmlPageFileName" value="" />
      New
    </s:link>
    <table border="1">
      <tr>
        <th>
          File
        </th>
        <th>
          Title
        </th>
        <th>
          Meta Description
        </th>
      </tr>
    <c:forEach var="htmlPageFile" items="${actionBean.htmlPageFileList}" >
      <tr>
        <td>
          <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean" event="view">
            <s:param name="htmlPageFileName" value="${htmlPageFile.fileName}" />
            ${htmlPageFile.fileName}
          </s:link>
        </td>
        <td>
          ${htmlPageFile.htmlPage.title}
        </td>
        <td>
          ${htmlPageFile.htmlPage.metaDescription}
        </td>
      </tr>
    </c:forEach>
    </table>
  </s:layout-component>
</s:layout-render>
    