package com.example.fileupload.service;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.TransformedResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Created by spielerl on 10/10/2017.
 */
public class ByteArrayWithFileNameResource extends TransformedResource {
    public ByteArrayWithFileNameResource(final String filename, final byte[] transformedContent) {
        super(new Resource() {
            @Override
            public boolean exists() {
                return true;
            }

            @Override
            public boolean isReadable() {
                return true;
            }

            @Override
            public boolean isOpen() {
                return true;
            }

            @Override
            public URL getURL() throws IOException {
                return null;
            }

            @Override
            public URI getURI() throws IOException {
                return null;
            }

            @Override
            public File getFile() throws IOException {
                return null;
            }

            @Override
            public long contentLength() throws IOException {
                return 0;
            }

            @Override
            public long lastModified() throws IOException {
                return 0;
            }

            @Override
            public Resource createRelative(String s) throws IOException {
                return null;
            }

            @Override
            public String getFilename() {
                return filename;
            }

            @Override
            public String getDescription() {
                return filename;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }
        }, transformedContent);
    }
}
