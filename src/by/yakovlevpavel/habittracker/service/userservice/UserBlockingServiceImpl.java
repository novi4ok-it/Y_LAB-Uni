package by.yakovlevpavel.habittracker.service.userservice;

import java.util.HashMap;
import java.util.Map;

public class UserBlockingServiceImpl implements UserBlockingService {
    private final Map<String, Boolean> blockedUsers = new HashMap<>();

    @Override
    public void blockUser(String email) {
        blockedUsers.put(email, true);
    }
}
