package com.david.deliverypizza.resource;

import com.david.deliverypizza.model.Borda;
import com.david.deliverypizza.repository.BordaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/borda")
    public class BordaResource {


    @Autowired
    private BordaRepository repository;

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "listar")
    public ResponseEntity listar(@RequestParam(value = "limit", defaultValue = "1") int limit,
                                 @RequestParam(value = "offset", defaultValue = "2") int offset,
                                 @RequestParam(value = "filter", required = false) String filter) {

        Pageable pageable = PageRequest.of(offset, limit,
                Sort.by(Sort.Direction.DESC, "id"));
        Page<Borda> bordaPage = repository.findAll(pageable);
        return new ResponseEntity<>(bordaPage, HttpStatus.OK);
    }

    //localhost:8090/bordas/1
    @RequestMapping(value = "{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable Long id) {
        Optional<Borda> borda = repository.findById(id);
        if (borda.isPresent()) {
            return new ResponseEntity<>(borda.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity post(@RequestBody @Valid Borda borda) {
        Borda bordaSaved = repository.save(borda);
        return new ResponseEntity<>(bordaSaved, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody Borda borda) {
        if (borda.getId().equals(id)) {
            Borda bordaUpdate =  repository.save(borda);
            return new ResponseEntity<>(bordaUpdate, HttpStatus.OK);

        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "{id}", method = DELETE)
    public ResponseEntity delete(@PathVariable ("{id}") Long id ){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
