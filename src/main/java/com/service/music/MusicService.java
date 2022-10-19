package com.service.music;

import com.model.Music;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MusicService implements IMusicService{
    private static Map<Long, Music> musicMap = new HashMap<>();
    private static Long autoPlusId = 0L;

    @Override
    public List<Music> findAll() {
        return new ArrayList<>(musicMap.values());
    }

    @Override
    public void save(Music music) {
        if (music.getId() == null) {
            music.setId(++autoPlusId);
        }
        musicMap.put(music.getId(), music);
    }

    @Override
    public void delete(Long id) {
        musicMap.remove(id);
    }

    @Override
    public List<Music> findByName(String name) {
        List<Music> list = new ArrayList<>();
        for (Music music: new ArrayList<>(musicMap.values())) {
            if (music.getName().contains(name)) {
                list.add(music);
            }
        }
        return list;
    }

    @Override
    public Music findByID(Long id) {
        return musicMap.get(id);
    }
}
