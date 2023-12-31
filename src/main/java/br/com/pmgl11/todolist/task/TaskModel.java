package br.com.pmgl11.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;


/**
 * ID
 * User (user_id)
 * Descricao
 * Titulo
 * Data Inicio
 * Data Termino
 * Prioridade
 */
@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @jakarta.persistence.Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID userId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception {
        if(title.length() > 50){
            throw new Exception("O titulo deve ter no maximo 50 caracteres");
        }
        this.title = title;
    }

}
