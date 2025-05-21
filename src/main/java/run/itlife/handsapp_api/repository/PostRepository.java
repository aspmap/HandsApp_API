package run.itlife.handsapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.itlife.handsapp_api.entity.Post;

import java.util.ArrayList;

// Уровень доступа к БД
// JpaRepository - специфически переносит методы для работы с реляционными БД.
//В JpaRepository есть все методы CRUD и много других.
//В репозиториях мы объявляем метод, не реализуя его и он по неймингу (если его правильно называем)
//автоматически понимает какой запрос нужно сделать.
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select p.*, u.username from post p " +
            "join users u on p.user_id = u.user_id " +
            "where u.username = ? order by p.created_at DESC " , nativeQuery = true)
    ArrayList<Post> findPostsByUsername(String username);
}