package monopoly.event;

import monopoly.GameControl;
import monopoly.Serialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveEvent implements ActionListener {

    GameControl game;

    public SaveEvent(GameControl _game){
        game = _game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println("Save");


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String dt_string = format.format(new Date().getTime());
        String file_name = "saves/monopoly_" + dt_string + ".bin";
        try{
            new File("saves").mkdir();
        }
        catch (Exception ex){
            ex.printStackTrace(System.err);
        }

        try{
            Files.write(new File(file_name).toPath(), Serialization.write_base64(game.getGameModel()).getBytes());
        }
        catch (Exception ex){
            ex.printStackTrace(System.err);
        }

        Serialization.write_base64(game.getGameModel());
    }
}
