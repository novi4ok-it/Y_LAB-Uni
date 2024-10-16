package by.yakovlevpavel.habittracker.service.userservice;

import java.util.Map;
public class UserUnblockingServiceImpl implements UserUnblockingService {
    private final Map<String, Boolean> blockedUsers;

    public UserUnblockingServiceImpl(Map<String, Boolean> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    @Override
    public void unblockUser(String email) {
        blockedUsers.remove(email);
    }
}
