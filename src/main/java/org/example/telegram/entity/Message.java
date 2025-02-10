package org.example.telegram.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

   private byte[] file;

    @ManyToOne
    private User from;
    @ManyToOne
    private User to;

    @CreationTimestamp
    private LocalDateTime dateTime;

    public String getTime() {
        String format = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return format;
    }
}
