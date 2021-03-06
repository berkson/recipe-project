package com.ximenes.recipeproject.controllers;

import com.ximenes.recipeproject.services.ImageService;
import com.ximenes.recipeproject.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Berkson Ximenes
 * Date: 06/07/2021
 * Time: 07:57
 */
@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    @PostMapping(value = "/recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);

        return String.format("redirect:/recipe/%s/show", id);
    }

    @GetMapping(value = "/recipe/{id}/recipeimage")
    public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) throws Exception {
        Byte[] image =  recipeService.findCommandById(Long.valueOf(id)).getImage();

        byte[] bytes = new byte[image.length];

        int i = 0;

        for (Byte b : image){
            bytes[i++] = b;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(bytes);
        IOUtils.copy(is, response.getOutputStream());
    }
}
