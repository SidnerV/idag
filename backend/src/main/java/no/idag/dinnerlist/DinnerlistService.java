package no.idag.dinnerlist;

import org.springframework.stereotype.Service;

@Service
public class DinnerlistService {
    private final DinnerlistRepository dinnerlistRepo;

    public DinnerlistService(DinnerlistRepository dinnerlistRepo) {
        this.dinnerlistRepo = dinnerlistRepo;
    }

    public Dinnerlist createDinnerlist(String name) {
        return this.dinnerlistRepo.save(new Dinnerlist(name));
    }
}
