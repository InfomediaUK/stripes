<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="htmlPage">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.HtmlPageActionBean">
    <s:errors/>
    <table class="form" >
      <tr>
        <td>
      <c:choose>
        <c:when test="${actionBean.htmlPageFileName==null}">
          Enter the simple Class name of a bean action followed by .xml<br />
          <s:text name="htmlPageFileName" class="required" size="103" />
        </c:when>
        <c:otherwise>
          ${actionBean.htmlPageFileName}
          <s:hidden name="htmlPageFileName" />
        </c:otherwise>
      </c:choose>
        </td>
      </tr>
      <tr>
        <td>
          Title<br />
          <s:text name="htmlPageToEdit.title" class="required" size="103" /> 
        </td>
      </tr>
      <tr>
        <td>
          Meta Description<br />
          <s:textarea name="htmlPageToEdit.metaDescription" class="required" cols="100" rows="3" />
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
        <s:label for="discipline" /><br />
        <s:select name="disciplineId" id="discipline" value="${actionBean.htmlPageToEdit.relatedDisciplineId}">
          <s:option value="">-----</s:option>
          <s:options-collection collection="${actionBean.disciplineList}" value="id" sort="displayOrder" />
        </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.htmlPageFileName!=null}">
          <s:submit name="delete" value="Delete"  />
          <s:submit name="revert" value="Revert"  />
         <s:submit name="download" value="Download"  />
        </c:if>
        </td>
      </tr>
    </table>
  </s:form>
  </s:layout-component>
</s:layout-render>
    