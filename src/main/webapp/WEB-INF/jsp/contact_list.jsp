<!--
 ! Excerpted from "Stripes: and Java Web Development is  Fun Again",
 ! published by The Pragmatic Bookshelf.
 ! Copyrights apply to this code. It may not be used to create training material, 
 ! courses, books, articles, and the like. Contact us if you are in doubt.
 ! We make no guarantees that this code is fit for any purpose. 
 ! Visit http://www.pragmaticprogrammer.com/titles/fdstr for more book information.
-->
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
  title="Contact List">
  <s:layout-component name="body">
    <d:table name="${actionBean.contacts}" id="contact" requestURI="" defaultsort="1">
      <d:column title="Last name" property="lastName" sortable="true"/>
      <d:column title="First name" property="firstName" sortable="true"/>
      <d:column title="Email" property="email" sortable="true"/>
    </d:table>
  </s:layout-component>
</s:layout-render>
