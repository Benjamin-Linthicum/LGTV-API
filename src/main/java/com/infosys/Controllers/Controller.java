package com.infosys.Controllers;

import java.io.FileReader;
import java.io.StringWriter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import com.infosys.Services.LGTVService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/lgtv")
public class Controller {

    private LGTVService service;
    
    @Autowired
    public Controller(LGTVService service){
        super();
        this.service = service;
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(){
        System.out.println("Test start");
        StringWriter writer = new StringWriter();
        ScriptContext context = new SimpleScriptContext();
        context.setWriter(writer);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("python");
        String[] args = {};
        engine.put(ScriptEngine.ARGV, args);
        try{
            engine.eval(new FileReader("../../../../python/com/infosys/filepathtest.py"), context);
            System.out.println(writer.toString());
            return ResponseEntity.status(200).build();
        } catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(500).build();
    }

    @PostMapping("/launch-app/{app}")
    public ResponseEntity<String> launch_app(@PathVariable("app") String app){
        if(service.launch_app(app)){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }

    @PostMapping("/set-mute")
    public ResponseEntity<String> set_mute(){
        if(service.set_mute()){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }

    @PostMapping("/close-app/{app}")
    public ResponseEntity<String> close_app(@PathVariable("app") String app){
        if(service.close_app(app)){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }

    @PostMapping("/set-volume/{volume}")
    public ResponseEntity<String> close_app(@PathVariable("volume") int volume){
        if(service.set_volume(volume)){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }

    @GetMapping("/is-registered")
    public ResponseEntity<Boolean> is_registered(){
        return ResponseEntity.ok(this.service.is_registered());
    }

    @GetMapping("/get-key-file-path")
    public ResponseEntity<String> get_key_file_path(){
        String key_file_path = this.service.get_key_file_path();
        if(!key_file_path.isEmpty()){
            return ResponseEntity.ok(key_file_path);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-current-app")
    public ResponseEntity<String> get_current_app(){
        String current_app = this.service.get_current_app();
        if(!current_app.isEmpty()){
            return ResponseEntity.ok(current_app);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-software-info")
    public ResponseEntity<String> get_software_info(){
        String software_info = this.service.get_software_info();
        if(!software_info.isEmpty()){
            return ResponseEntity.ok(software_info);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-audio-status")
    public ResponseEntity<String> get_audio_status(){
        String audio_status = this.service.get_audio_status();
        if(!audio_status.isEmpty()){
            return ResponseEntity.ok(audio_status);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-volume")
    public ResponseEntity<String> get_volume(){
        String volume = this.service.get_volume();
        if(!volume.isEmpty()){
            return ResponseEntity.ok(volume);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/power-off")
    public ResponseEntity<String> power_off(){
        if(service.power_off()){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }

    @PostMapping("/power-on")
    public ResponseEntity<String> power_on(){
        if(service.power_on()){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }

    @PostMapping("/turn-3d-on")
    public ResponseEntity<String> turn_3d_on(){
        if(service.turn_3d_on()){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }

    @PostMapping("/turn-3d-off")
    public ResponseEntity<String> turn_3d_off(){
        if(service.turn_3d_off()){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }

}
