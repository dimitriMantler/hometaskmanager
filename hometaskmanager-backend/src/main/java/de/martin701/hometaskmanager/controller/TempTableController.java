package de.martin701.hometaskmanager.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import de.martin701.hometaskmanager.entity.TempTable;
import de.martin701.hometaskmanager.exception.ResourceNotFoundException;
import de.martin701.hometaskmanager.repository.TempTableRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TempTableController {
    @Autowired
    private TempTableRepository temptableRepository;

    @GetMapping("/temptables")
    public List <TempTable> getAllTempTables() {
        return temptableRepository.findAll();
    }

    @GetMapping("/temptables/{id}")
    public ResponseEntity < TempTable > getTempTableById(@PathVariable(value = "id") Long temptableId)
            throws ResourceNotFoundException {
        TempTable temptable = temptableRepository.findById(temptableId)
                .orElseThrow(() -> new ResourceNotFoundException("TempTable not found for this id :: " + temptableId));
        return ResponseEntity.ok().body(temptable);
    }

    @PostMapping("/temptables")
    public TempTable createTempTable(@Valid @RequestBody TempTable temptable) {
        return temptableRepository.save(temptable);
    }

    @PutMapping("/temptables/{id}")
    public ResponseEntity < TempTable > updateTempTable(@PathVariable(value = "id") Long temptableId,
                                                      @Valid @RequestBody TempTable temptableDetails) throws ResourceNotFoundException {
        TempTable temptable = temptableRepository.findById(temptableId)
                .orElseThrow(() -> new ResourceNotFoundException("TempTable not found for this id :: " + temptableId));

        temptable.setTempCol(temptableDetails.getTempCol());
        final TempTable updatedTempTable = temptableRepository.save(temptable);
        return ResponseEntity.ok(updatedTempTable);
    }

    @DeleteMapping("/temptables/{id}")
    public Map < String, Boolean > deleteTempTable(@PathVariable(value = "id") Long temptableId)
            throws ResourceNotFoundException {
        TempTable temptable = temptableRepository.findById(temptableId)
                .orElseThrow(() -> new ResourceNotFoundException("TempTable not found for this id :: " + temptableId));

        temptableRepository.delete(temptable);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}