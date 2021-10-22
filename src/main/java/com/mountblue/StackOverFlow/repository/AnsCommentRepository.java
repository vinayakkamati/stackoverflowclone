package com.mountblue.StackOverFlow.repository;

import com.mountblue.StackOverFlow.model.AnsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnsCommentRepository extends JpaRepository<AnsComment, Integer> {
}
