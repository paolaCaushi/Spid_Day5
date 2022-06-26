package com.example.demo.model;

import com.example.demo.utilities.Status;
import com.example.demo.utilities.Type;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Spid")
public class Spid extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User userId;

    private Status status;
    private Type type;


    public Spid(LocalDateTime createdAt, String createdBy, long id, User userId, Status status, Type type) {
        super(createdAt, createdBy);
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.type = type;
    }

    public Spid() {

    }

}
