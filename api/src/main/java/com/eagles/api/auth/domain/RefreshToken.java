package com.eagles.api.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "refresh_token")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "expires_in", nullable = false)
    private Instant expiresIn;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}
