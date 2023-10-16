package br.com.pmgl11.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.pmgl11.todolist.user.IUserRespository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRespository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();

                if(servletPath.startsWith("/tasks/")){
                    // pegar a auth ( user e senha)
                    var Authorization = request.getHeader("Authorization");
                    
                    var authEncoded = Authorization.substring("Basic".length()).trim();

                    byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
                    
                    var authString = new String(authDecoded);

                    System.out.println("Authorization");
                    System.out.println(authString);

                    String[] credentials = authString.split(":");
                    String username = credentials[0];
                    String password = credentials[1];

                    // validar user
                    var user = this.userRepository.findByUsername(username);
                    if (user == null){
                        response.sendError(401, "User sem autorização");
                    } else {
                        // validar senha
                        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                        if(passwordVerify.verified){
                            request.setAttribute("userId", user.getId());
                            filterChain.doFilter(request, response);
                        } else {
                            response.sendError(401, "User sem autorização");
                        }
                    }
                } else {
                    filterChain.doFilter(request, response);
                }
    
    }

}
