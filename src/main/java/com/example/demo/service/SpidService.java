package com.example.demo.service;
import com.example.demo.model.Spid;
import com.example.demo.model.User;
import com.example.demo.repository.SpidRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.utilities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SpidService {

    @Autowired
    private SpidRepo spidRepo;
    @Autowired
    private UserRepo userRepo;

    public SpidService() {
    }


    public Spid findSpidById(long id) throws Exception {
        Optional<Spid> spid = spidRepo.findById(id);

        if (spid.isEmpty()) {
            throw new Exception("This spid does not exists");
        }

        return spid.get();
    }


    //create a spid
    @Transactional
    public Spid createSpid(Spid spid) throws Exception {
        Optional<Spid> currentSpid = spidRepo.findSpidByUserId(spid.getUserId());
        Optional<User> user = userRepo.findById(spid.getUserId().getId());

        if (user.isEmpty()) {
            throw new Exception("This user does not exists");
        }


        if (currentSpid.isPresent()) {
            throw new Exception("This user has already created a spid");
        }

        spid.setCreatedBy(user.get().getUsername());

        return spidRepo.save(spid);
    }


    public Iterable<Spid> retrieveAllSpids() {

        return spidRepo.findAll();
    }


    public Spid changeStatus(long id) throws Exception {
        Spid spid = findSpidById(id);
        spid.setStatus(Status.READY_FOR_REVIEW);
        return spidRepo.save(spid);
    }

    //delete specific spid
    public void deleteSpid(long id) throws Exception {

        Optional<Spid> spid  = spidRepo.findById(id); // Find by spid id

        // Checking is the item they are trying to find is already in the db
        if (spid.isEmpty()) {
            throw new Exception("This SPID does not exists");
        }

        // Checking for spid status before trying to actually delete it
        if (spid.get().getStatus() != Status.PENDING) {
            throw new Exception("You are not allowed to delete this SPID");
        }

        // Finally after all checks did complete delete the spid
        spidRepo.delete(spid.get());
    }
}