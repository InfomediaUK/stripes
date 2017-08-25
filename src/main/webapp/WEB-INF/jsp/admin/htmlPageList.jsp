<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="htmlPageList">
  <s:layout-component name="body">
    <s:errors/>
    <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean" event="view">
      <s:param name="htmlPageFileName" value="" />
      New
    </s:link>
    <table>
      <tr>
        <th align="left">Page (ActionBean)</th>
        <th align="left">Title - Description - Keywords</th>
      </tr>
      <tbody>
    <c:forEach var="htmlPageFile" items="${actionBean.htmlPageFileList}" varStatus="status">
      <c:choose>
        <c:when test='${(status.index)%2 eq 0}'>
          <c:set var="rowColor" value="odd" scope="page"/>
        </c:when>
        <c:otherwise>
          <c:set var="rowColor" value="even" scope="page"/>
        </c:otherwise>
      </c:choose>
      <tr class="${rowColor}">
        <td valign="top">
          <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean" >
            <s:param name="htmlPageFileName" value="${htmlPageFile.fileName}" />
            ${htmlPageFile.shortName}
          </s:link>
        </td>
        <td>
          <table>
            <tr>
              <td>
                ${htmlPageFile.htmlPage.title}
              </td>
            </tr>
            <tr>
              <td>
                ${htmlPageFile.htmlPage.metaDescription}
              </td>
            </tr>
            <tr>
              <td>
                ${htmlPageFile.htmlPage.metaKeywords}
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </c:forEach>
      </tbody>
    </table>
<%--     
    <d:table name="${actionBean.htmlPageFileList}" id="htmlPageFile" requestURI="" defaultsort="1" >
      <d:column title="File" sortable="true" >
          <s:link beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean" >
            <s:param name="htmlPageFileName" value="${htmlPageFile.fileName}" />
            ${htmlPageFile.shortName}
          </s:link>
      </d:column>
      <d:column title="Title" property="htmlPage.title" sortable="true" />
      <d:column title="Meta Description" property="htmlPage.metaDescription" sortable="true" />
    </d:table>
--%>
  </s:layout-component>
</s:layout-render>
    