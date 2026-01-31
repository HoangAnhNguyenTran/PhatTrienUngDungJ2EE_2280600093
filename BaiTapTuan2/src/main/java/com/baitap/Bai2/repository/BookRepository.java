package com.baitap.Bai2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.baitap.Bai2.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
