
package com.north.music.service;

import com.north.music.model.Music;
import com.north.music.repository.MusicRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {
    
   @Autowired
   MusicRepository musicRepository;
    
    
   public Music criar(Music mus){
       mus.setId(null);
       musicRepository.save(mus);
       return mus;
   }
   
   public List<Music> listarTodos(){
       return musicRepository.findAll();
   }
   
   public Music buscarPorId(Integer id){
       return musicRepository.findById(id).orElseThrow();
   }
   
   public void excluir(Integer id){
       Music musicEncontrado = buscarPorId(id); //valida se existe id informado
       musicRepository.deleteById(musicEncontrado.getId());
   }
   
   public Music atualizar(Integer id, Music musicEnviado){
       Music musicEncontrado = buscarPorId(id);
       musicEncontrado.setName(musicEnviado.getName());
       musicEncontrado.setArtist(musicEnviado.getArtist());
       musicEncontrado.setImage(musicEnviado.getImage());
       musicEncontrado.setAudio(musicEnviado.getAudio());
       musicRepository.save(musicEncontrado);
       return musicEncontrado;
   } 
   
}
