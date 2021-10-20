package com.mountblue.StackOverFlow.repository;

import com.mountblue.StackOverFlow.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
