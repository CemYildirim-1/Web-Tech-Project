package htwberlin.webtech.service;

import htwberlin.webtech.persistence.NotizbucheintragEntity;
import htwberlin.webtech.persistence.NotizenRepository;
import htwberlin.webtech.web.api.Notizbucheintrag;
import htwberlin.webtech.web.api.NotizbucheintragCreateRequest;
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

        notizbucheintragEntity= notizenRepository.save(notizbucheintragEntity);

        return transformEntity(notizbucheintragEntity);


    }
    public Notizbucheintrag create(NotizbucheintragCreateRequest request) {

        var notizbucheintragEntity = new NotizbucheintragEntity(request.getLdt(), request.getEntry());
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
        return new Notizbucheintrag(
                entity.getId(),
                entity.getLdt(),
                entity.getEntry()
        );

    }
}
