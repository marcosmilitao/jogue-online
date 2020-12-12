package net.jogueonline.service;

import net.jogueonline.domain.Loteria;
import net.jogueonline.repository.LoteriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoteriaService {
    private final Logger log = LoggerFactory.getLogger(BilheteService.class);
    private final LoteriaRepository loteriaRepository;

    public LoteriaService(LoteriaRepository loteriaRepository) {
        this.loteriaRepository = loteriaRepository;
    }

    public List<Loteria> listarLoterias(Long id){
        List<Loteria> lista = new ArrayList<>();
        List<Loteria> l = loteriaRepository.findByBanca(id);

        l.forEach(loteria -> {
            if(validarLoteria(loteria)){
                lista.add(loteria);
            }
        });

        return lista;
    }

    public Boolean validarLoteria(Loteria loteria){
        Instant nowUtc = Instant.now();
        ZoneId brasilSaoPaulo = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime nowBrasil = ZonedDateTime.ofInstant(nowUtc,brasilSaoPaulo);

        if(loteria.getHora() < nowBrasil.getHour() && loteria.getMinuto() < nowBrasil.getMinute()){
            return true;
        } else {
            return false;
        }

    }
}
