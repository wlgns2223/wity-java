package im.wity.service;

import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendEmail( @Email String email) {

        try{
            Thread.sleep(1000);
            return true;
        }  catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
