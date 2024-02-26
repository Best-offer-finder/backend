package com.example.backend.setting.repository;

import com.example.backend.setting.Key;
import com.example.backend.setting.model.ApplicationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationSettingRepository extends JpaRepository<ApplicationSetting, String> {

    Optional<ApplicationSetting> findByKey(Key key);

}
