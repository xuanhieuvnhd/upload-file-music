package com.controller;

import com.model.Music;
import com.model.MusicForm;
import com.service.music.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/song")
public class MusicController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IMusicService musicService;

    @GetMapping("")
    public ModelAndView home() {
        List<Music> musicList = musicService.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("musics", musicList);
        return modelAndView;
    }

    @GetMapping("/save")
    public ModelAndView createForm() {
        return new ModelAndView("create", "musicForm", new MusicForm());
    }

    @PostMapping("/save")
    public ModelAndView createMusic(@ModelAttribute MusicForm musicForm) {
        MultipartFile multipartFile = musicForm.getLink();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(musicForm.getLink().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Music music = new Music(musicForm.getId(), musicForm.getName(), fileName, musicForm.getCategory());
        musicService.save(music);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("musicForm", musicForm);
        if (musicForm.getId() == null) {
            modelAndView.addObject("message", "Created new song successfully !");
        } else {
            modelAndView.addObject("message", "Updated song successfully !");
        }
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView playMusic(@PathVariable("id") Long id) {
        Music music = musicService.findByID(id);
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("music", music);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public String deleteMusic(@PathVariable("id") Long id, Model model) {
        musicService.delete(id);
        model.addAttribute("message", "Delete successfully !");
        return "redirect:/song";
    }

    @GetMapping("{id}/edit")
    public ModelAndView editInfo(@PathVariable("id") Long id) {
        Music music = musicService.findByID(id);
        return new ModelAndView("create", "musicForm", new MusicForm(music.getId(),
                music.getName(), null, music.getCategory()));
    }
}
