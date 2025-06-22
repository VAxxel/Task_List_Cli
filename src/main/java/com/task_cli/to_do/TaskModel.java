package com.task_cli.to_do;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "TASK")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID", columnDefinition = "NUMBER")
    private long taskId;
    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR2(50)")
    private String descripcion;
    @Column(name = "STATUS", columnDefinition = "NVARCHAR2(15)")
    private String status;
    @Column(name = "CREATEDAT", columnDefinition = "TIMESTAMP")
    private Timestamp createdAt;
    @Column(name = "UPDATEDAT", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;


}
