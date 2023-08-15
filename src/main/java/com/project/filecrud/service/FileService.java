package com.project.filecrud.service;

import com.project.filecrud.entity.File;
import com.project.filecrud.enums.FileExtension;
import com.project.filecrud.repository.FileRepository;
import com.project.filecrud.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public FileVO save(FileVO fileVO){
        File fileToSave = new File();
        fileToSave.setName(fileVO.getName());
        fileToSave.setSize(fileVO.getSize());
        fileToSave.setExtension(FileExtension.valueOf(fileVO.getExtension()));
        fileToSave.setBytes(fileVO.getBytes());

        fileToSave = fileRepository.save(fileToSave);
        fileVO.setId(fileToSave.getId());
        return fileVO;
    }
}
