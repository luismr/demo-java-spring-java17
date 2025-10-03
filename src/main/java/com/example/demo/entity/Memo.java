package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "memo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Memo implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description", nullable = false)
  private String description;
  @Column(name = "done", nullable = false)
  private Boolean done;
  @Column(name = "updated", nullable = false)
  private LocalDateTime updated;

  public static Memo of(String title, String description) {
    return Memo.of(null, title, description);
  }

  public static Memo of(Long id, String title, String description) {
    return Memo.builder()
        .id(id)
        .title(title)
        .description(description)
        .done(false)
        .updated(LocalDateTime.now())
        .build();
  }

  public void merge(Memo memo) {
    if (memo.title != null && memo.title.length() > 0) {
      title = memo.title;
    }
    if (memo.description != null && memo.description.length() > 0) {
      description = memo.description;
    }
    if (memo.done != null) {
      done = memo.done;
    }
  }

  @PrePersist
  private void prePersist() {
    done = false;
    updated = LocalDateTime.now();
  }

  @PreUpdate
  private void preUpdate() {
    updated = LocalDateTime.now();
  }

}
