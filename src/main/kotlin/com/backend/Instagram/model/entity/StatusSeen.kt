package com.backend.Instagram.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity(name = "status_seen")
data class StatusSeen(
    @Id
    var id: String = UUID.randomUUID().toString(),
    var userId: String = "",
    var statusId: String = "",
    var alreadySeen: Boolean = false,
    @JsonIgnore
    @ManyToOne
    var status: Status = Status()
)
