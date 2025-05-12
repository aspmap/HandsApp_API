package run.itlife.handsapp_api.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import run.itlife.handsapp_api.entity.User;

//Интерфейс, отвечающий за логику создания пользователей, поиск пользователей
public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}
