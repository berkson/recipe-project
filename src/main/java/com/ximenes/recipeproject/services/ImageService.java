package com.ximenes.recipeproject.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Berkson Ximenes
 * Date: 06/07/2021
 * Time: 08:02
 */
public interface ImageService {

    void saveImageFile(Long id, MultipartFile file);

}
