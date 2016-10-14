package filtro;


import bean.UsuarioMB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lhries
 */
@WebFilter(filterName = "FiltroAcessoAdmin", urlPatterns = {"/faces/admin/*"})
public class FiltroAcessoAdmin implements Filter {
    @Inject
    UsuarioMB usuarioMB;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("Verificando acesso do admin!");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //LoginMB loginMB = (LoginMB) req.getSession().getAttribute("loginMB");
        if(usuarioMB!=null && usuarioMB.estaLogado() && usuarioMB.eFuncionario())
            chain.doFilter(request, response);
        else
            resp.sendRedirect(req.getContextPath()+"/faces/login.xhtml");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
