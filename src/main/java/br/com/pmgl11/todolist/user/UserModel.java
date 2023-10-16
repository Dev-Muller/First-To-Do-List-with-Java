package br.com.pmgl11.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// @Getter coloca apenas os getters sem os setters
// @Setter coloca apenas os setters sem os getters
@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    // colocando o @ em cima de um atributo vc o metodo apenas para aquele atributo 
    // por exemplo @Getter vai ser aplicado apenas no username
    // @Column(name = "usuario") da um nome para a coluna no banco de dados
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // o @Data substitui os metodos getters e setters pelo lombok
    // public void setUsername(String username) {
    //     this.username = username;
    // }

    // public String getUsername() {
    //     return this.username;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    // public String getPassword() {
    //     return password;
    // }

    // // metodos getters e setters
}
