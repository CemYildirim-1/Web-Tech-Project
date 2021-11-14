package htwberlin.webtech.web;


import htwberlin.webtech.service.NotizbucheintragService;
import htwberlin.webtech.web.api.Notizbucheintrag;
import htwberlin.webtech.web.api.NotizbucheintragCreateRequest;
import org.springframework.boot.autoconfigure.data.mongo.ReactiveStreamsMongoClientDependsOnBeanFactoryPostProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class NotizbucheintragRestController {

    private final NotizbucheintragService notizbucheintragService;

    public NotizbucheintragRestController(NotizbucheintragService notizbucheintragService) {
        this.notizbucheintragService = notizbucheintragService;
    }


    @GetMapping(path = "/api/v1/notizen")
    public ResponseEntity<List<Notizbucheintrag>> listNotizen() {
        return ResponseEntity.ok(notizbucheintragService.findAll());
    }


    @GetMapping(path = "/api/v1/notizen/{id}")
    public ResponseEntity<Notizbucheintrag> findPersonById(@PathVariable Long id) {


        var notizbucheintrag = notizbucheintragService.findById(id);

        return notizbucheintrag != null ? ResponseEntity.ok(notizbucheintrag) : ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/api/v1/notizen/{id}")
    public ResponseEntity<Notizbucheintrag> updateNotizById(@PathVariable Long id, @RequestBody NotizbucheintragCreateRequest request) {

        var notizbucheintrag = notizbucheintragService.updateNotizbucheintrag(id, request);

        return notizbucheintrag != null ? ResponseEntity.ok(notizbucheintrag) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/notizen/{id}")
    public ResponseEntity<Void> deleteNotizbucheintrag(@PathVariable Long id) {
        boolean successful = notizbucheintragService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


    @PostMapping(path = "/api/v1/notizen")
    public ResponseEntity<Void> createNotizbucheintrag(@RequestBody NotizbucheintragCreateRequest request) throws URISyntaxException {

        var notizbucheintrag = notizbucheintragService.create(request);
        URI uri = new URI("/api/v1/notizen/" + notizbucheintrag.getId());
        return ResponseEntity.created(uri).build();


    }
}
