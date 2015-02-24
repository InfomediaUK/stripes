<%@ include file="/WEB-INF/jsp/common/pagedirectives.jsp" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
  title="Contact List">
  <s:layout-component name="body">
    <table>
      <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
      </tr>
      <c:forEach var="contact" items="${actionBean.contacts}">
        <tr>
          <td>${contact.firstName}</td>
          <td>${contact.lastName}</td>
          <td>${contact.email}</td>
        </tr>
      </c:forEach>
    </table>
  </s:layout-component>
</s:layout-render>
