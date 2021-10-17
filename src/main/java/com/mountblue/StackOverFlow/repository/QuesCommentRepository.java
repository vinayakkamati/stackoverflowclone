package com.mountblue.StackOverFlow.repository;

import com.mountblue.StackOverFlow.model.QuesComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuesCommentRepository extends JpaRepository<QuesComment, Integer> {

}
