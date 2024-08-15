package com.mymosaic.common.file;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Component
public class FileManger {

    public String loadImage(String filePath) throws IOException {
        File file = new File(filePath);

        byte[] bytes = FileCopyUtils.copyToByteArray(file);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /*
    * 파일이 저장될 최종 경로 반환
    * */
    public String getFullPath(String fileDir, String filename) {
        return fileDir + filename;
    }

    /*
    * 여러 파일 저장
    * */
    public List<UploadFile> storeFiles(String fileDir, List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(fileDir, multipartFile));
            }
        }
        return storeFileResult;
    }

    /*
    * 단일 파일 저장
    * */
    public UploadFile storeFile(String fileDir, MultipartFile multipartFile) throws IOException
    {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename(); //파일 이름 가져오기
        String storeFileName = createStoreFileName(originalFilename); //저장할 파일 이름 생성
        multipartFile.transferTo(new File(getFullPath(fileDir, storeFileName))); //해당 경로에 파일 저장
        return new UploadFile(originalFilename, storeFileName, getFullPath(fileDir, storeFileName)); //저장한 파일 이름 반환
    }

    /*
    * 저장 파일 이름 생성
    * */
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    /*
    * 파일 확장자 추출
    * */
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
