package com.example.fileupload.service;

import com.example.fileupload.db.StorageEntity;
import com.example.fileupload.db.StorageEntityRepository;
import com.example.fileupload.excpetion.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by spielerl on 07/10/2017.
 */
@Service
@Profile(value = "default")
public class DBStorageEntitySerivce implements StorageService {

    @Autowired
    StorageEntityRepository storageEntityRepository;

    @Override
    public void init() {    }

    @Override
    @Transactional
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {
            // This is a security check
            throw new StorageException("Cannot store file with relative path outside current directory "+ filename);
        }
        try {
            StorageEntity storageEntity = new StorageEntity();
            storageEntity.setContentType(file.getContentType());
            storageEntity.setFileName(filename);
            storageEntity.setSize(file.getSize());
            storageEntity.setContent(file.getBytes());
            storageEntityRepository.save(storageEntity);

        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }



    }

    @Override
    @Transactional(readOnly = true)
    public Stream<Path> loadAll() {
        List<StorageEntity> all = storageEntityRepository.findAll();
        return all.stream().map(se -> Paths.get(se.getFileName()) );
    }

    @Override
    @Transactional(readOnly = true)
    public Path load(String filename) {
        return Paths.get(filename);
    }

    @Override
    @Transactional(readOnly = true)
    public Resource loadAsResource(final String filename) {
        StorageEntity byFileName = storageEntityRepository.findByFileName(filename);
        return new ByteArrayWithFileNameResource(filename , byFileName.getContent());
    }

    @Override
    @Transactional
    public void deleteAll() {
        storageEntityRepository.deleteAll();
    }
}
