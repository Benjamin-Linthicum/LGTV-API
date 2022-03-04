package com.infosys.Services;

import java.io.FileReader;
import java.io.StringWriter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LGTVService {
    
    private StringWriter writer;
    private ScriptContext context;
    private ScriptEngineManager manager;
    private ScriptEngine engine;

    @Autowired
    public LGTVService(){
        super();
    }
    
    private boolean execute(String[] args){
        this.writer = new StringWriter();
        this.context = new SimpleScriptContext();
        context.setWriter(writer);
        this.manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("python");
        this.engine.put(ScriptEngine.ARGV, args);
        try{
            this.engine.eval(new FileReader("../../../../python/com/infosys/lgtv-python.py"), context);
            if(this.writer.toString().equals("Success")){
                return true;
            }
            else{
                System.out.println("Python: " + this.writer.toString());
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private String execute_with_output(String[] args){
        this.writer = new StringWriter();
        this.context = new SimpleScriptContext();
        context.setWriter(writer);
        this.manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("python");
        this.engine.put(ScriptEngine.ARGV, args);
        try{
            this.engine.eval(new FileReader("../../../../python/com/infosys/lgtv-python.py"), context);
            if(this.writer.toString().contains("Error connecting to the TV")){
                System.out.println("Failed to connect to the TV.");
                return "";
            }
            return this.writer.toString();
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Failed to read any output.");
        return "";
    }

    public boolean launch_app(String app){
        String[] args = {"launch-app", app};
        return this.execute(args);
    }

    public boolean close_app(String app){
        String[] args = {"close-app", app};
        return this.execute(args);
    }

    public boolean set_volume(int volume){
        String[] args = {"close-app", Integer.toString(volume)};
        return this.execute(args);
    }

    public boolean set_mute(){
        String[] args = {"set-mute"};
        return this.execute(args);
    }

    public boolean power_off(){
        String[] args = {"power-off"};
        return this.execute(args);
    }

    public boolean power_on(){
        String[] args = {"power-on"};
        return this.execute(args);
    }

    public boolean turn_3d_on(){
        String[] args = {"turn-3d-on"};
        return this.execute(args);
    }

    public boolean turn_3d_off(){
        String[] args = {"turn-3d-off"};
        return this.execute(args);
    }

    public boolean is_registered(){
        String[] args = {"is-registered"};
        return this.execute(args);
    }

    public String get_key_file_path(){
        String[] args = {"get-key-file-path"};
        return this.execute_with_output(args);
    }

    public String get_current_app(){
        String[] args = {"get-current-app"};
        return this.execute_with_output(args);
    }

    public String get_software_info(){
        String[] args = {"get-software-info"};
        return this.execute_with_output(args);
    }

    public String get_audio_status(){
        String[] args = {"get-audio-status"};
        return this.execute_with_output(args);
    }

    public String get_volume(){
        String[] args = {"get-volume"};
        return this.execute_with_output(args);
    }

}
