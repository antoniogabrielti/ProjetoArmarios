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
@WebFilter(filterName = "FiltroLogin", urlPatterns = {"/faces/login.xhtml"})
public class FiltroLogin implements Filter {
    @Inject 
    UsuarioMB usuarioMB;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Verificando login!");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //LoginMB loginMB = (LoginMB) req.getSession().getAttribute("loginMB");
        if(usuarioMB!=null && usuarioMB.estaLogado()){
            if(usuarioMB.eFuncionario())
                resp.sendRedirect(req.getContextPath()+"/faces/admin/index.xhtml");
            else
                resp.sendRedirect(req.getContextPath()+"/faces/aluno/index.xhtml");
        }            
        else
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
