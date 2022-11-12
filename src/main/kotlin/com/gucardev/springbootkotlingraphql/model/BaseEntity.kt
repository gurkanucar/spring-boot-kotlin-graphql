package com.gucardev.springbootkotlingraphql.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass


@MappedSuperclass
abstract class BaseEntity : Serializable {

    abstract  var id: Long?

    @Column(updatable = false)
    @CreationTimestamp
    val createdAt: OffsetDateTime? = null

    @UpdateTimestamp
    val updatedAt: OffsetDateTime? = null

}