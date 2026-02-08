package app.near.us.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditEntity {

    @CreatedDate
    @Column(name = "CREATE_AT", nullable = false , updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "UPDATE_AT",insertable = false)
    private LocalDateTime updateAt;

    @CreatedBy
    @Column(name = "CREATE_BY",updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "UPDATE_BY")
    private String updatedBy;

}
