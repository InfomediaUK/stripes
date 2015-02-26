<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/admin/layoutMain.jsp" >
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
      <c:choose>
        <c:when test="${actionBean.htmlPageFileName==null}">
          Enter the simple Class name of a bean action followed by .xml<br />
          <s:text name="htmlPageFileName" size="103" />
        </c:when>
        <c:otherwise>
          ${actionBean.htmlPageFileName}
        </c:otherwise>
      </c:choose>
        </td>
      </tr>
      <tr>
        <td>
          Title<br />
          <s:text name="htmlPageToEdit.title" size="103" />
        </td>
      </tr>
      <tr>
        <td>
          Meta Description<br />
          <s:textarea name="htmlPageToEdit.metaDescription" cols="100" rows="3" />
        </td>
      </tr>
      <tr>
        <td>
          Meta Keywords<br />
          <s:textarea name="htmlPageToEdit.metaKeywords" cols="100" rows="1" />
        </td>
      </tr>
      <tr>
        <td>
          Body<br />
          <s:textarea name="htmlPageToEdit.body" cols="100" rows="20"  />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="save"  />
          <s:submit name="cancel" value="cancel"  />
        <c:if test="${actionBean.htmlPageFileName!=null}">
          <s:submit name="delete" value="delete"  />
          <s:submit name="download" value="download"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="htmlPageFileName" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    