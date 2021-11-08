package monopoly;

import javax.swing.*;
import java.awt.*;

/**
 * monopoly.DialogProvider provides easy debuging dialogs for the game.
 */
public class DialogProvider {
    public static DialogProvider self;

    public static boolean debug_mode = false;
    public static int result_confirm = JOptionPane.NO_OPTION;
    public static String result_input = "";

    /**
     * Singleton Mode
     * @return
     */
    public static DialogProvider getInstance(){
        if(self == null){
            self = new DialogProvider();
        }
        return self;
    }

    /**
     * Show a dialog with the given message.
     * @param parentComponent parent component
     * @param message message to show
     */
    public void show_message_dialog(Component parentComponent, String message){
        if(!debug_mode){
            JOptionPane.showMessageDialog(parentComponent, message);
        }else{
            System.err.println("monopoly.DialogProvider::show_message_dialog -> " + message);
        }
    }

    /**
     * Shows a confirm dialog with the given message.
     * @param parentComponent parent component
     * @param message message to show
     * @return Confirm dialog result
     */
    public int show_confirm_dialog(Component parentComponent, String message){
        if(debug_mode){
            return JOptionPane.showConfirmDialog(parentComponent, message);
        }else{
            return result_confirm;
        }
    }

    /**
     * Shows an input dialog with the given message.
     * @param parentComponent parent component
     * @param message message to show
     * @return Input dialog result
     */
    public String show_input_dialog(Component parentComponent, String message){
        if(!debug_mode){
            return JOptionPane.showInputDialog(parentComponent, message);
        }else{
            return result_input;
        }
    }
}
