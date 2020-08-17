package com.lihe.algorithm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("execute")
    public ModelAndView execute(Map<String, Object> map){
        Config config = new Config.Builder().rowCnt(21).colCnt(41).build();

        RandomLabyrinth randomLabyrinth = new RandomLabyrinth();
        List<List<String>> lists = randomLabyrinth.generateLabyrinth(config);

        map.put("itemlist", lists);
        return new ModelAndView("labyrinth");
    }
}
