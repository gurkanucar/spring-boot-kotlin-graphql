package com.gucardev.springbootkotlingraphql.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*


@MappedSuperclass
abstract class BaseEntity : Serializable {

    abstract  var id: Long?

    @Column(updatable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
     val updatedAt: LocalDateTime? = null

}