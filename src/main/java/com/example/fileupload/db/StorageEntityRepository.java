package com.example.fileupload.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by spielerl on 07/10/2017.
 */
@Repository
public interface  StorageEntityRepository extends JpaRepository<StorageEntity, Long> {
    StorageEntity findByFileName(String fileName);
}
