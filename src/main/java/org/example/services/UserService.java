package org.example.services;

import org.example.model.User;
import org.example.utils.R;

public class UserService {

    public User getUser() {
        User user = new User();
        user.setName(R.TEST_DATA.getString("name"));
        user.setLastName(R.TEST_DATA.getString("last_name"));
        user.setEmail(R.TEST_DATA.getString("email"));
        return user;
    }


}
