package com.Ecom.AuthService.Repository;

public interface CustomQueries {
    String FIND_USER_LATEST_SESSION = "SELECT * FROM Session_db e ORDER BY e.login_at DESC LIMIT 1";
}
