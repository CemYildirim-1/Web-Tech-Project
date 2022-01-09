package HTWBERLIN.webtech.service;

import HTWBERLIN.webtech.persistence.Colour;
import HTWBERLIN.webtech.persistence.NotizenRepository;
import HTWBERLIN.webtech.web.api.Notizbucheintrag;
import HTWBERLIN.webtech.persistence.NotizbucheintragEntity;
import HTWBERLIN.webtech.web.api.NotizbucheintragCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotizbucheintragService {



    private final NotizenRepository notizenRepository;

    public NotizbucheintragService(NotizenRepository notizenRepository) {
        this.notizenRepository = notizenRepository;
    }



    public List<Notizbucheintrag> findAll() {

        List<NotizbucheintragEntity> notizen = notizenRepository.findAll();

        return notizen.stream().map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Notizbucheintrag findById(Long id){

        var notizbucheintragEntity = notizenRepository.findById(id);

        return notizbucheintragEntity.isPresent()? transformEntity(notizbucheintragEntity.get()) : null;
    }


    public Notizbucheintrag updateNotizbucheintrag(Long id, NotizbucheintragCreateRequest request){
        var notizbucheintragEntityOptional = notizenRepository.findById(id);
        if (notizbucheintragEntityOptional.isEmpty()) {

            return null;
        }

        var  notizbucheintragEntity = notizbucheintragEntityOptional.get();

        notizbucheintragEntity.setLdt(request.getLdt());
        notizbucheintragEntity.setEntry(request.getEntry());
     //   notizbucheintragEntity.setColour(Colour.valueOf(request.getColour()));
        notizbucheintragEntity.setColour(request.getColour());

        notizbucheintragEntity= notizenRepository.save(notizbucheintragEntity);

        return transformEntity(notizbucheintragEntity);


    }
    public Notizbucheintrag create(NotizbucheintragCreateRequest request) {
        //var colour = Colour.valueOf(request.getColour());
        var notizbucheintragEntity = new NotizbucheintragEntity(request.getLdt(), request.getEntry(), request.getColour());
        notizenRepository.save(notizbucheintragEntity);
        return transformEntity(notizbucheintragEntity);
    }

    public boolean deleteById (Long id) {
        if (!notizenRepository.existsById(id)){

            return false;
        }

        notizenRepository.deleteById(id);
        return true;
    }




    public Notizbucheintrag transformEntity(NotizbucheintragEntity entity) {
        //var colour = entity.getColour() != null ? entity.getColour().name() : Colour.unknown.name();
        return new Notizbucheintrag(
                entity.getId(),
                entity.getLdt(),
                entity.getEntry(),
                entity.getColour()
        );

    }
}
