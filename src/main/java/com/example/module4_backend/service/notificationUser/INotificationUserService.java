package com.example.module4_backend.service.notificationUser;

import com.example.module4_backend.model.entity.NotificationUser;
import com.example.module4_backend.service.IGeneralService;

import java.util.List;

public interface INotificationUserService extends IGeneralService<NotificationUser> {
    List<NotificationUser> showALl(Long id);
}
