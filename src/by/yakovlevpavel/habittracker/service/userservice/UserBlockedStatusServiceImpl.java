package by.yakovlevpavel.habittracker.service.userservice;

import java.util.Map;

public class UserBlockedStatusServiceImpl implements UserBlockedStatusService {
    private final Map<String, Boolean> blockedUsers;

    public UserBlockedStatusServiceImpl(Map<String, Boolean> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    @Override
    public boolean isUserBlocked(String email) {
        return blockedUsers.getOrDefault(email, false);
    }
}
