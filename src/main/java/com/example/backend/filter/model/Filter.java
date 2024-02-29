package com.example.backend.filter.model;

import com.example.backend.user.model.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 8192)
    private String data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "filter")
    private Set<FilterToCar> filterToCars = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<FilterToCar> getFilterToCars() {
        return filterToCars;
    }

    public void setFilterToCars(Set<FilterToCar> filterToCars) {
        this.filterToCars = filterToCars;
    }

}
