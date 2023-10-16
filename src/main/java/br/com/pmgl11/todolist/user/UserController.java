package br.com.pmgl11.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/* Modificadores
 * public
 * private
 * protected
 * static
 */

 @RestController
 @RequestMapping("/users")
public class UserController {
    
    /**
     * String (texto)
     * Interger (número inteiro)
     * Double (double, numero decimais) numeros 0.0000
     * Float (float) Numeros 0.000
     * char (A C )
     * Date (data)
     * void (sem nenhum retorno)
     */
    @Autowired
    private IUserRespository userRespository;
    /**
      * Body
      * 
      */
      @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
      var user = this.userRespository.findByUsername(userModel.getUsername());

      if (user != null) {
        System.out.println("Usuário já existe");
        // mensagem de erro e status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
      }

      var passwordHased = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());

      userModel.setPassword(passwordHased);

      var userCreated = this.userRespository.save(userModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
