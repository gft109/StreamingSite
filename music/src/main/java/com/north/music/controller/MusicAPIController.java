
package com.north.music.controller;

import com.north.music.model.Music;
import com.north.music.service.MusicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
public class MusicAPIController {
    
    @Autowired
    MusicService musicService;

    //cadastrar(C)
    @PostMapping("/adicionar")
    public ResponseEntity<Music> addMusic(@RequestBody Music mus) {
        var novoMusic = musicService.criar(mus);
        return new ResponseEntity<>(novoMusic, HttpStatus.CREATED);
    }

    //listar(R)
    @GetMapping("/listar")
    public ResponseEntity<List> listar() {
        List<Music> musics = musicService.listarTodos();
        return new ResponseEntity<>(musics, HttpStatus.OK);
    }

    //buscar(R)
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Music> pesquisar(@PathVariable Integer id) {
        Music musicEncontrado = musicService.buscarPorId(id);
        return new ResponseEntity<>(musicEncontrado, HttpStatus.OK);
    }

    //alterar(U)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Music> editMusic(@PathVariable Integer id, @RequestBody Music mus) {
        var editarMusic = musicService.atualizar(id, mus);
        return new ResponseEntity<>(editarMusic, HttpStatus.OK);
    }

    //excluir(D)
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        musicService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
