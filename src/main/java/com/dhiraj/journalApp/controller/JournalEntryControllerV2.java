package com.dhiraj.journalApp.controller;

import com.dhiraj.journalApp.entity.JournalEntry;
import com.dhiraj.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getAll")
    public List<JournalEntry> getAll(){
       return journalEntryService.getAll();
    }

    @PostMapping("/create")
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry){
        return journalEntryService.saveEntry(journalEntry);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
      Optional<JournalEntry> journalEntry= journalEntryService.getEntryById(id);
      if(journalEntry.isPresent()){
          return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
      }else {
          return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/id/{myId}")
    public void deleteJournalEntryById(@PathVariable ObjectId myId){
      journalEntryService.deleteEntryById(myId);
      System.out.println("Deleted");
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateJournal(@PathVariable ObjectId myId,
                                      @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getEntryById(myId).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().isEmpty() ?
                    newEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!= null && !newEntry.getContent().isEmpty() ?
                    newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }
}
