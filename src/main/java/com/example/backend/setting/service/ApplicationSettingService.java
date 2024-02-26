package com.example.backend.setting.service;

import com.example.backend.common.ErrorCode;
import com.example.backend.exception.exceptions.EntityNotFoundException;
import com.example.backend.setting.Key;
import com.example.backend.setting.model.ApplicationSetting;
import com.example.backend.setting.repository.ApplicationSettingRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationSettingService {

    private final ApplicationSettingRepository applicationSettingRepository;

    public ApplicationSettingService(ApplicationSettingRepository applicationSettingRepository) {
        this.applicationSettingRepository = applicationSettingRepository;
    }

    public String getString(Key key) throws EntityNotFoundException {
        ApplicationSetting applicationSetting = applicationSettingRepository.findByKey(key).orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_SETTING_NOT_FOUND));
        return applicationSetting.getValue();
    }
}
