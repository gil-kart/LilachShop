package org.lilachshop.panels;

import org.lilachshop.commonUtils.Socket;
import org.lilachshop.entities.User;
import org.lilachshop.requests.SignOutRequest;

public class SignedInEmployeePanel extends Panel {

    public SignedInEmployeePanel(Socket socket, Object controller) {
        super(socket, controller);
    }

    public void sendSignOutRequestToServer(User employee) {
        sendToServer(new SignOutRequest(SignOutRequest.Messages.SIGN_OUT, employee));
    }
}
