package com.aluraflix.Controller.repository;

import com.aluraflix.Controller.entities.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideosRepository extends JpaRepository<Videos, UUID> {
}
