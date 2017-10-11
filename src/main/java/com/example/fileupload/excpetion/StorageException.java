package com.example.fileupload.excpetion;

/**
 * Created by spielerl on 07/10/2017.
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

