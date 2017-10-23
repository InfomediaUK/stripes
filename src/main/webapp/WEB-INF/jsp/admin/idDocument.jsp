<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/admin/layout/layoutMain.jsp"
  title="${actionBean.htmlPage.title}" 
  metaDescription="${actionBean.htmlPage.metaDescription}"
  metaKeywords="${actionBean.htmlPage.metaKeywords}"
  pageHeading="idDocument">
  <s:layout-component name="body">
  <s:form beanclass="net.infomediauk.stripes.action.admin.IdDocumentActionBean">
    <s:errors/><s:messages/>
    <table class="form" >
      <tr>
        <td>
          <s:label for="id" />
        </td>
        <td>
        <c:if test="${actionBean.idDocument.id==null}">
          <s:text name="idDocument.id" id="id" class="required" size="4" />
        </c:if>  
        <c:if test="${actionBean.idDocument.id!=null}">
           ${actionBean.idDocument.id}
           <s:hidden name="idDocument.id" />
        </c:if>  
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="code" />
        </td>
        <td>
          <s:text name="idDocument.code" id="code" size="6" />
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="idDocument" />
        </td>
        <td>
          <s:text name="idDocument.name" id="idDocument" class="required" size="100" />
        </td>
      </tr>
      <tr>
        <td>
          ID Document Type
        </td>
        <td>
        <s:select name="idDocument.idDocumentType" id="idDocument.idDocumentType" value="${actionBean.idDocument.idDocumentType}">
          <s:option value="0">IdDocument</s:option>
          <s:option value="1">ID Card</s:option>
        </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          Requires Visa
        </td>
        <td>
        <s:select name="idDocument.requiresVisa" id="idDocument.requiresVisa" value="${actionBean.idDocument.requiresVisa}">
          <s:option value="true">Yes</s:option>
          <s:option value="false">No</s:option>
        </s:select> 
        </td>
      </tr>
      <tr>
        <td>
          <s:label for="displayOrder" />
        </td>
        <td>
          <s:text name="idDocument.displayOrder" id="displayOrder" class="required" size="3" />
        </td>
      </tr>
      <tr>
        <td>
          <s:submit name="save" value="Save"  />
          <s:submit name="cancel" value="Cancel"  />
        <c:if test="${actionBean.idDocument.id!=null}">
          <s:submit name="delete" value="Delete"  />
        </c:if>
        </td>
      </tr>
    </table>
    <s:hidden name="idDocument.numberOfChanges" />
  </s:form>
  </s:layout-component>
</s:layout-render>
    