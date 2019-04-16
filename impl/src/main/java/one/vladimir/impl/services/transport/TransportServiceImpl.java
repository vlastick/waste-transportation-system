package one.vladimir.impl.services.transport;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("transportService")
public class TransportServiceImpl {

    @PostConstruct
    public void postConstructLog(){
        System.out.println("transportService initialized");
    }

}
