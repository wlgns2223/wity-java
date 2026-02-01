package im.wity.service;

import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendEmail( @Email String email) throws InterruptedException {

        Thread.sleep(1000);
        return true;
    }
}
