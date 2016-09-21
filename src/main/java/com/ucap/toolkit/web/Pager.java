package com.ucap.toolkit.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ucap.toolkit.type.IntUtil;
import com.ucap.toolkit.type.StringUtil;

/**
 * <p>
 * client invoke demo :
 * </p>
 * <li> String url = "foo.servlet";
 * <li> List arg_name = Arrays.asList( "a1", "a2" );
 * <li> List arg_value = Arrays.asList( "v1", "v1" );
 * <li> HttpServletRequest req = getRequest();
 * <li> Pager p = new Pager(req,url, arg_name, arg_value);
 * <li> String html = p.toHtml();
 * <p>
 * hibernate demo :
 * </p>
 * <li> setFirstResult( ( p.getCurPage() - 1 ) * p.getPageSize() );
 * <li> setMaxResults( p.getPageSize() );
 * <li> p.setTotalHits( hits );
 */
public class Pager {

    // pager
    private int pageSize = 10;
    private int curPage = 1;
    private int totalPage = 0;
    private int totalHits = 0;

    // links
    private HttpServletRequest req;
    private String url;
    @SuppressWarnings("unchecked")
    private List argNames;
    @SuppressWarnings("unchecked")
    private List argValues;

    @SuppressWarnings("unchecked")
    public Pager(HttpServletRequest req, String url, List argNames, List argValues) {
        this.req = req;
        this.url = url;
        this.argNames = argNames;
        this.argValues = argValues;
        read_client_params();
    }

    private void read_client_params() {
        pageSize = IntUtil.toInt( req.getParameter( "pageSize" ), 10 );
        curPage = IntUtil.toInt( req.getParameter( "curPage" ), 1 );
    }

    public String toHtml() {

        StringBuffer html = new StringBuffer();
        contact_url_with_args();

        // current page
        html.append( "<a class='grey' href='javascript:void(0)' >" + curPage + "</a>" );
        int max_side_btn = 6;
        int side_btn_remain = max_side_btn;

        // buttons on the left
        int min_left = curPage - max_side_btn;
        for ( int left = curPage - 1; ( left >= min_left && left > 0 ); left-- ) {
            String href = url + "&curPage=" + left;
            html.insert( 0, "<a href='" + href + "' >" + left + "</a>" );
            side_btn_remain--;
        }
        if ( side_btn_remain > 0 ) max_side_btn += side_btn_remain;

        // buttons on the right
        int max_right = curPage + max_side_btn;
        for ( int right = curPage + 1; ( right <= totalPage && right <= max_right ); right++ ) {
            String href = url + "&curPage=" + right;
            html.append( "<a class='right' href='" + href + "' >" + right + "</a>" );
        }

        // first page
        String first_url = url + "&curPage=1";
        if ( curPage == 1 ) html.insert( 0, "<a class='grey' href='javascript:void(0)' >首页</a>" );
        else html.insert( 0, "<a href='" + first_url + "' >首页</a>" );

        // last page
        String last_url = url + "&curPage=" + totalPage;
        if ( curPage == totalPage ) html.append( "<a class='grey' href='javascript:void(0)' >尾页</a>" );
        else html.append( "<a href='" + last_url + "' >尾页</a>" );

        // drop down list
        html.append( "<select onchange='change_page_size(this.value)'>" );
        for ( int i = 1; i <= 5; i++ ) {
            int ps = i * 10;
            if ( pageSize == ps ) html.append( "<option selected value='" + ps + "'>" + ps + "</option>" );
            else html.append( "<option value='" + ps + "'>" + ps + "</option>" );
        }
        html.append( "</select>" );
        String fakeUrl = url.replace( "pageSize", "foo" );
        html.append( "<script>function change_page_size(ps){location.href='" + fakeUrl + "&pageSize='+ps;}</script>" );
        html.append( "<style>#jk_pager {margin-top: 10px;} #jk_pager a,#jk_pager select {border: 1px solid #ccc;padding: 5px;background-color: #fff;}#jk_pager a.grey {color: red;}</style>" );

        html.insert( 0, "<div id='jk_pager'> 共 [" + totalHits + "] 条记录, 每页 [" + pageSize + "] 条, 共 [" + totalPage + "] 页&nbsp;&nbsp;&nbsp;" );
        html.append( "</div>" );
        return html.toString();
    }

    private void contact_url_with_args() {
        String base = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/";

        url = base + url + "?pageSize=" + pageSize;
        for ( int i = 0; i < argNames.size(); i++ ) {
            Object v = argValues.get( i );
            if ( !StringUtil.isEmpty( v ) ) {
                url += "&" + argNames.get( i ) + "=" + v.toString().trim();
            }
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        if ( totalHits > 0 ) {
            int bar = totalHits / pageSize;
            if ( ( totalHits % pageSize ) > 0 ) {
                bar += 1;
            }
            this.totalPage = bar;
        }
        this.totalHits = totalHits;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}
