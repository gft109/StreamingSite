
package com.north.music.controller;

import com.north.music.model.Music;
import com.north.music.service.MusicService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MusicController {
 
    @Autowired 
    MusicService musicService;
      
    @GetMapping("/") //eh definido a URL que sera chamada
    public String inicio(Model model,@RequestParam(name = "index", defaultValue = "1") String index){
        List<Music> musicList = musicService.listarTodos();
        Integer indexOfList = Integer.parseInt(index);
        Music musicPlaying = musicList.get(indexOfList - 1);
        model.addAttribute("indexOfList",indexOfList);
        model.addAttribute("musicPlaying",musicPlaying);
        model.addAttribute("lista", musicService.listarTodos() );
        return "index"; //sera apontado o arquivo HTML que sera chamado
    }
    
    @GetMapping("/inserir-music") //eh definido a URL que sera chamada
    public String cadastroForm(Model model){
        model.addAttribute("music", new Music());
        return "index"; //sera apontado o arquivo HTML que sera chamado
    }  
    
    @GetMapping("/listagem") //eh definido a URL que sera chamada
    public String listaMusic(Model model, @RequestParam(name = "index", defaultValue = "1") String index){
        List<Music> musicList = musicService.listarTodos();
        Integer indexOfList = Integer.parseInt(index);
        indexOfList = indexOfList - 1;
        Music musicPlaying = musicList.get(indexOfList);
        model.addAttribute("musicPlaying",musicPlaying);
        model.addAttribute("lista", musicService.listarTodos() );
        return "listagem"; //sera apontado o arquivo HTML que sera chamado
    }
    
    @GetMapping("/changeMusic") //eh definido a URL que sera chamada
    public String changeMusic(Model model, @RequestParam(name = "index", defaultValue = "1") String index){
        List<Music> musicList = musicService.listarTodos();
        Integer indexOfList = Integer.parseInt(index);
        Music musicPlaying = musicList.get(indexOfList - 1);
        model.addAttribute("indexOfList",indexOfList);
        model.addAttribute("musicPlaying",musicPlaying);
        return "redirect:/?index=" + indexOfList; //sera apontado o arquivo HTML que sera chamado
    }
    
    /*
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Music music, Model model){
        
        if (music.getId()!=null) {
           musicService.atualizar(music.getId(), music);
        } else {
            musicService.criar(music);
        }
        return "redirect:/listagem";
    }
    */
    @GetMapping("/excluir")
    public String excluirMusic(Model model, @RequestParam String id){
        Integer idMusic = Integer.parseInt(id);
        musicService.excluir(idMusic);
        return "redirect:/listagem";
    }
    
    @GetMapping("/alterar")
    public String alterarMusic(Model model, @RequestParam String id){    
        Integer idMusic = Integer.parseInt(id);
        Music musicEncontrado = musicService.buscarPorId(idMusic);
        model.addAttribute("music",musicEncontrado);
        return "alterar";
    }
    
    
}
