package com.dhiraj.journalApp.service;

import com.dhiraj.journalApp.entity.JournalEntry;
import com.dhiraj.journalApp.repo.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public JournalEntry saveEntry(JournalEntry journalEntry){
      return  journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deleteEntryById(ObjectId id){
         journalEntryRepo.deleteById(id);
    }

}
