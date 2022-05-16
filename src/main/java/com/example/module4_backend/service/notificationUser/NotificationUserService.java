package com.example.module4_backend.service.notificationUser;

import com.example.module4_backend.model.entity.NotificationUser;
import com.example.module4_backend.repository.INotificationFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NotificationUserService implements INotificationUserService{
    @Autowired
    private INotificationFriendRepository notificationRepository;
    @Override
    public Page<NotificationUser> findALl(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Override
    public Optional<NotificationUser> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public NotificationUser save(NotificationUser notificationUser) {
        return notificationRepository.save(notificationUser);
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<NotificationUser> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public List<NotificationUser> showALl(Long id) {
        return notificationRepository.showALl(id);
    }
}
