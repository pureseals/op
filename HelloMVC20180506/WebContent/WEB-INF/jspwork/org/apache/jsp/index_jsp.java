/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.50
 * Generated at: 2018-04-25 13:44:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t관람등급\r\n");
      out.write("\t영화제목\r\n");
      out.write("\t영문제목\r\n");
      out.write("\t타입 : 디지털 자막 ATMOS(자막), 3D 자막 3D ATMOS(자막) etc\r\n");
      out.write("\t개봉일 : 2018.04.25\r\n");
      out.write("\t감독 : 안소니 루소, 조 루소\r\n");
      out.write("\t출연진 : 로버트 다우니 주니어, 조슈 브롤린, 마크 러팔로, 톰 히들스턴, 크리스 에반스, 크리스 헴스워스, 제레미 래너,...\r\n");
      out.write("\t장르 : SF, 액션, 판타지, 어드벤처\r\n");
      out.write("\t러닝타임 149min\r\n");
      out.write("\t누적관객 : \r\n");
      out.write("\t전일관객 : \r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t줄거리\r\n");
      out.write("\t\r\n");
      out.write("\t스틸컷\r\n");
      out.write("\t동영상\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<label for=\"movie_name\">영화제목</label>\r\n");
      out.write("\t<input type=\"text\" name=\"movie_name\" id=\"movie_name\"/>\r\n");
      out.write("\t\r\n");
      out.write("\t<label for=\"movie_name\">영화영문제목</label>\r\n");
      out.write("\t<input type=\"text\" name=\"movie_ename\" id=\"movie_ename\"/>\r\n");
      out.write("\t\r\n");
      out.write("\t<label for=\"movie_name\">감독</label>\r\n");
      out.write("\t<input type=\"text\" name=\"movie_ename\" id=\"movie_ename\"/>\r\n");
      out.write("\t\r\n");
      out.write("\t<label for=\"movie_casting\">출연진</label>\r\n");
      out.write("\t<input type=\"text\" name=\"movie_casting\" id=\"movie_casting\"/>\r\n");
      out.write("\r\n");
      out.write("\t<label for=\"movie_running_time\">러닝타임</label>\r\n");
      out.write("\t<input type=\"text\" name=\"movie_running_time\" id=\"movie_running_time\"/>\r\n");
      out.write("\t\r\n");
      out.write("\t<input type=\"checkbox\" name=\"movie_genre\" id=\"\" />\r\n");
      out.write("\t<label for=\"movie_name\">감독</label>\r\n");
      out.write("\t드라마\r\n");
      out.write("\t판타지\r\n");
      out.write("\t서부\r\n");
      out.write("\t공포\r\n");
      out.write("\t멜로/로맨스\r\n");
      out.write("\t모험\r\n");
      out.write("\t스릴러\r\n");
      out.write("\t느와르\r\n");
      out.write("\t컬트\r\n");
      out.write("\t다큐멘터리\r\n");
      out.write("\t코미디\r\n");
      out.write("\t가족\r\n");
      out.write("\t미스터리\r\n");
      out.write("\t전쟁\r\n");
      out.write("\t애니메이션\r\n");
      out.write("\t범죄\r\n");
      out.write("\t뮤지컬\r\n");
      out.write("\tSF\r\n");
      out.write("\t액션\r\n");
      out.write("\t무협\r\n");
      out.write("\t에로\r\n");
      out.write("\t서스펜스\r\n");
      out.write("\t서사\r\n");
      out.write("\t블랙코미디\r\n");
      out.write("\t실험\r\n");
      out.write("\t공연실황\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t등급\r\n");
      out.write("\t\r\n");
      out.write("\t전체관람가\r\n");
      out.write("\t12세 관람가\r\n");
      out.write("\t15세 관람가\r\n");
      out.write("\t청소년 관람불가\r\n");
      out.write("\t제한상영가\r\n");
      out.write("\t등급보류\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t장르테이블\r\n");
      out.write("\t등급 테이블\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
