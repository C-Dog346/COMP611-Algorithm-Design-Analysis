/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Assignment_1;

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
        System.out.println(this.getId());

        synchronized (this) {
            notifyAll();
        }
    }
}
