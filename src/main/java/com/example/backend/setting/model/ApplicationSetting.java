package com.example.backend.setting.model;

import com.example.backend.setting.Key;
import jakarta.persistence.*;

@Entity
@Table(name = "application_setting")
public class ApplicationSetting {

    @Id
    @Column(name = "\"key\"", length = 128)
    @Enumerated(EnumType.STRING)
    private Key key;

    @Column(name = "value", length = 8192)
    private String value;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
