package htwberlin.webtech.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotizenRepository extends JpaRepository<NotizbucheintragEntity,Long> {

    List<NotizbucheintragEntity> findAllById(Long id);

}
