package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Berkson Ximenes
 * Date: 06/07/2021
 * Time: 08:03
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(id).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);

        } catch (IOException e) {
            log.error("Error occurred: ", e);
        }
    }
}
