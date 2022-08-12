/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Assignment_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Callum
 */
public class MessageSender extends Task {

    public MessageSender(Object param) {
        super(param);
    }

    @Override
    public void run() {
       // synchronized (this) {
            this.notifyAll(null);
      //  }
        
        System.out.println("Start of message " + this.getId());
        
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("End of message " + this.getId());
    }
}
