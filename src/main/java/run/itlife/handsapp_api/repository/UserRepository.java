package run.itlife.handsapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import run.itlife.handsapp_api.entity.User;

import java.util.Optional;

// Уровень доступа к БД
// JpaRepository - специфически переносит методы для работы с реляционными БД.
//В JpaRepository есть все методы CRUD и много других.
//В репозиториях мы объявляем метод, не реализуя его и он по неймингу (если его правильно называем)
//автоматически понимает какой запрос нужно сделать.
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Возвращает юзера
    User findByUsername(User user); // Возвращает юзера

}
