package com.project.filecrud.service;

import com.project.filecrud.converter.ByteArrayConverter;
import com.project.filecrud.converter.FileToDTOConverter;
import com.project.filecrud.dto.FileDTO;
import com.project.filecrud.entity.File;
import com.project.filecrud.enums.FileExtension;
import com.project.filecrud.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileRepository fileRepository;
    private final FileToDTOConverter fileToDTOConverter;

    public FileDTO save(FileDTO fileDTO){
        log.info("File Service is saving file | fileName : {}", fileDTO.getName());
        File file = new File();
        file.setName(fileDTO.getName());
        file.setSize(fileDTO.getSize());
        file.setExtension(FileExtension.valueOf(fileDTO.getExtension()));
        file.setBytes(ByteArrayConverter.convertStringBytesToBytes(fileDTO.getBytes()));

        file = fileRepository.save(file);

        fileDTO.setId(file.getId());
        log.info("File saved successfully | fileName : {}, id : {}", fileDTO.getName(), fileDTO.getId());
        return fileDTO;
    }


    public FileDTO retrieve(Integer id){
        log.info("File Service retrieve file | id : {}", id);
        Optional<File> file = fileRepository.findById(id);
        if(file.isEmpty()) throw new ObjectNotFoundException(id, File.class.getSimpleName());
        log.info("File retrieved | id : {}, fileName : {}",id, file.get().getName());

        return fileToDTOConverter.convert(file.get());
    }

    public FileDTO update(Integer id, FileDTO fileDTO){
        log.info("File Service is updating file | fileName : {}", fileDTO.getName());
        Optional<File> file = fileRepository.findById(id);
        if(file.isEmpty()) throw new ObjectNotFoundException(id, file.getClass().getName());

        file.get().setName(fileDTO.getName());
        file.get().setExtension(FileExtension.valueOf(fileDTO.getExtension()));
        file.get().setSize(fileDTO.getSize());
        file.get().setBytes(ByteArrayConverter.convertStringBytesToBytes(fileDTO.getBytes()));
        fileRepository.save(file.get());

        fileDTO.setId(id);
        log.info("File updated successfully | fileName : {}, id : {}", fileDTO.getName(), fileDTO.getId());
        return fileDTO;
    }

    public Integer delete(Integer id){
        log.info("File Service is deleting file with | id : {}", id);
        fileRepository.deleteById(id);
        log.info("File Service deleted file with | id : {}", id);
        return id;
    }
}
