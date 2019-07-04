package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "totalEncoding",value = "utf-8")
        }
)
public class TotalFilter implements Filter {
    protected String encoding;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (encoding!=null){
            req.setCharacterEncoding(encoding);
            resp.setCharacterEncoding(encoding);
            resp.setContentType("text/html;charset=utf-8");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding=config.getInitParameter("totalEncoding");
    }

}
