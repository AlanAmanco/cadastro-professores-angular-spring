package amanco.alan.cadastroProfessor.controller;

import amanco.alan.cadastroProfessor.model.entity.Professor;
import amanco.alan.cadastroProfessor.model.repository.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/professor")
@CrossOrigin("*")
public class ProfessorController {
    private final static Logger logger = Logger.getLogger(ProfessorController.class.getName());


    @Autowired
    private ProfessorRepository repository;

    @Operation(summary = "Obter todos os professores")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter os professores",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Professor[].class)) })
    @GetMapping
    public ResponseEntity<?> obterTodos() throws Exception {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e){
            logger.log(Level.SEVERE, "Ocorreu um erro ao obter os professores", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao obter os professores", e);
        }
    }

    @Operation(summary = "Obter todos os professores com Documentos Entregues ")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter os professores",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor[].class)) })
    @GetMapping("/documentos-entregues")
    public ResponseEntity<?> obterTodosDocumentosEntregues() {
        try {
            List<Professor> professores = repository.findAllDocumentosEntregues();
            return ResponseEntity.ok(professores);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao obter os professores", e);
        }
    }

    @Operation(summary = "Salvar um professor")
    @ApiResponse(responseCode = "201", description = "Professor salvo com sucesso",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class)) })
    @ApiResponse(responseCode = "500", description = "Erro interno ao salvar o professor")
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Professor professor) {
        try {

            Professor professorSalvo = repository.save(professor);
            return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ocorreu um erro ao salvar o professor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obter um professor pelo ID")
    @ApiResponse(responseCode = "200", description = "Professor encontrado com sucesso",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class)) })
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    @GetMapping("{id}")
    public ResponseEntity<?> obterProfessorPorId(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.ok(repository.findById(id));
        } catch (Exception e){
            logger.info(e.getMessage());
        }
        return (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    @Operation(summary = "Deletar um professor pelo ID")
    @ApiResponse(responseCode = "204", description = "Professor deletado com sucesso",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class)) })
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado com ID: " + id);
        }
    }

    @Operation(summary = "Atualizar um professor pelo ID")
    @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class)) })
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Professor clienteAtualizado) {
        try {
            return repository.findById(id)
                    .map(professor -> {
                        clienteAtualizado.setId(professor.getId());
                        return ResponseEntity.ok(repository.save(clienteAtualizado));
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado com ID: " + id));
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar Professor", e);
        }
    }



}
