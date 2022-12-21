package com.exchangeinformat.userprofile.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "username")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String username;

    @Column(name = "first_name")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String firstName;

    @Column(name = "last_name")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String lastName;

    @Column(name = "middle_name")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String middleName;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "email")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String email;

    @Column(name = "phone")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "gender")
    @EqualsAndHashCode.Include
    private String gender;

    @Column(name = "inn")
    private String inn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "users_products",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_banned")
    private boolean isBanned;

    @Column(name = "token")
    private String token;

}
