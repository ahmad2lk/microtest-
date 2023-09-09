package recipemanager.projekt.recipemanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import recipemanager.projekt.recipemanager.model.Instruction;
import recipemanager.projekt.recipemanager.service.InstructionService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/instruction")
//@PreAuthorize("hasRole('ADMIN')")
public class InstructionController {


    private final InstructionService instructionService;

    public InstructionController(InstructionService instructionService) {

        this.instructionService = instructionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instruction>> getAllInstructions() {
        List<Instruction> instructions = instructionService.findAllInstructions();
        return new ResponseEntity<>(instructions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Instruction> getInstructionById(@PathVariable("id") Long id) {
        Instruction instruction = instructionService.findInstructionById(id);
        return new ResponseEntity<>(instruction, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Instruction> addInstruction(@RequestBody Instruction instruction) {
        Instruction newInstruction = instructionService
                .addInstruction(instruction );
        return new ResponseEntity<>(newInstruction, HttpStatus.CREATED);
    }


}
