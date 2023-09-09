package recipemanager.projekt.recipemanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import recipemanager.projekt.recipemanager.model.CookMixInstruction;
import recipemanager.projekt.recipemanager.model.Instruction;
import recipemanager.projekt.recipemanager.service.CookMixInstructionService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cookMixInstruction")
//@PreAuthorize("hasRole('ADMIN')")
public class CookMixInstructionController {


    private final CookMixInstructionService cookMixInstructionService;

    public CookMixInstructionController(CookMixInstructionService cookMixInstructionService) {

        this.cookMixInstructionService = cookMixInstructionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CookMixInstruction>> getAllCookMixInstructions() {

        List<? extends Instruction> instructions = cookMixInstructionService.findAllCookMixInstructions();
        List<CookMixInstruction> cookMixInstructions = new ArrayList<>(instructions.size());
        for (Instruction instruction : instructions) {
            cookMixInstructions.add((CookMixInstruction) instruction);
        }

        return new ResponseEntity<>(cookMixInstructions, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CookMixInstruction> getCookMixInstructionById(@PathVariable("id") Long id) {
        CookMixInstruction cookMixInstruction = cookMixInstructionService.findCookMixInstructionById(id);
        return new ResponseEntity<>(cookMixInstruction, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CookMixInstruction> addCookMixInstruction(@RequestBody CookMixInstruction cookMixInstruction) {
        CookMixInstruction newCookMixInstruction = cookMixInstructionService
                .addCookMixInstruction(cookMixInstruction );
        return new ResponseEntity<>(newCookMixInstruction, HttpStatus.CREATED);
    }


}
