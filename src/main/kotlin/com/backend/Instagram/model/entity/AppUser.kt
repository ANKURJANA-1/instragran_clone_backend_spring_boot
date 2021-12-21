package com.backend.Instagram.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import java.util.stream.StreamSupport
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "app_users")
data class AppUser(
    @Id
    val id: String = UUID.randomUUID().toString(),
    @Column(name = "first_name", nullable = false, length = 30)
    var firstName: String = "",
    @Column(name = "last_name", nullable = false, length = 30)
    var lastName: String = "",
    @Column(name = "user_name", nullable = false, length = 30, unique = true)
    var userName: String = "",
    @Column(name = "user_email", nullable = false, unique = true, length = 30)
    var userEmail: String = "",
    @JsonIgnore
    @Column(name = "user_password", nullable = false, length = 30)
    var userPassword: String = "",
    @Column(name = "user_place", nullable = false, length = 20)
    var place: String = "",
    @Column(name = "user_mobile_no", nullable = false, length = 15)
    var mobileNo: String = "",
    @Column(name = "user_country", nullable = false, length = 15)
    var country: String = "",
    var profilePicUrl: String = "",
    var active: Boolean = true,
    @Column(name = "user_country_code", nullable = false)
    var countryCode: String = "",
    var profileComplete: Boolean = false,
    @OneToMany(mappedBy = "appUser")
    var post: List<Post> = ArrayList(),
    @OneToMany(mappedBy = "appUser")
    var status: List<Status> = ArrayList(),
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var updateAt: LocalDateTime = LocalDateTime.now()
)
