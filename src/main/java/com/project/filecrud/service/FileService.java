package com.project.filecrud.service;

import com.project.filecrud.converter.FileToVOConverter;
import com.project.filecrud.entity.File;
import com.project.filecrud.enums.FileExtension;
import com.project.filecrud.repository.FileRepository;
import com.project.filecrud.vo.FileVO;
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
    private final FileToVOConverter fileToVOConverter;

    public FileVO save(FileVO fileVO){
        log.info("File Service is saving file | fileName : {}", fileVO.getName());
        File fileToSave = new File();
        fileToSave.setName(fileVO.getName());
        fileToSave.setSize(fileVO.getSize());
        fileToSave.setExtension(FileExtension.valueOf(fileVO.getExtension()));
        fileToSave.setBytes(fileVO.getBytes());

        fileToSave = fileRepository.save(fileToSave);

        fileVO.setId(fileToSave.getId());
        log.info("File saved successfully | fileName : {}, id : {}", fileVO.getName(), fileVO.getId());
        return fileVO;
    }


    public FileVO retrieve(Integer id){
        log.info("File Service retrieve file | id : {}", id);
        Optional<File> file = fileRepository.findById(id);
        if(file.isEmpty()) throw new ObjectNotFoundException(id, file.getClass().getName());
        log.info("File retrieved | id : {}, fileName : {}",id, file.get().getName());

        return fileToVOConverter.convert(file.get());
    }

    public FileVO update(Integer id, FileVO fileVO){
        log.info("File Service is updating file | fileName : {}", fileVO.getName());
        Optional<File> file = fileRepository.findById(id);
        if(file.isEmpty()) throw new ObjectNotFoundException(id, file.getClass().getName());

        file.get().setName(fileVO.getName());
        file.get().setExtension(FileExtension.valueOf(fileVO.getExtension()));
        file.get().setSize(fileVO.getSize());
        file.get().setBytes(fileVO.getBytes());
        fileRepository.save(file.get());

        fileVO.setId(id);
        log.info("File updated successfully | fileName : {}, id : {}", fileVO.getName(), fileVO.getId());
        return fileVO;
    }
}
