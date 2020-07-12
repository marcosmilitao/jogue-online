package net.jogueonline.service;

import net.jogueonline.domain.Aposta;
import net.jogueonline.domain.Bilhete;
import net.jogueonline.repository.ApostaRepository;
import net.jogueonline.repository.BilheteRepository;
import net.jogueonline.util.DateTimeInstantUtil;
import net.jogueonline.web.rest.BilheteResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing bilhete.
 */
@Service
@Transactional
public class BilheteService {

    private final Logger log = LoggerFactory.getLogger(BilheteService.class);
    private final ApostaRepository apostaRepository;
    private final BilheteRepository bilheteRepository;

    public BilheteService(ApostaRepository apostaRepository, BilheteRepository bilheteRepository) {
        this.apostaRepository = apostaRepository;
        this.bilheteRepository = bilheteRepository;
    }

    public void salvarApostas(Bilhete bilhete) {
        this.apostaRepository.saveAll(bilhete.getApostas());
    }

    public Bilhete validarBilhete(Bilhete bilhete){
        if(bilhete == null){
            return null;
        }
        if(bilhete.getDataHoraAposta() == null){
            bilhete.setDataHoraAposta(DateTimeInstantUtil.getDataTimeNowBr());
        }
        return bilhete;
    }
    public Bilhete gerarNumQrcBilhete(Bilhete bilhete) {
        bilhete.setNumeroBilhete(Long.parseLong(String.valueOf(bilhete.getCodigoBanca())+String.valueOf( bilhete.getCodigoTerminal())+String.valueOf(bilhete.getId())));
        bilhete.setQrcode(bilhete.getNumeroBilhete().toString() + bilhete.getDataHoraAposta().toString());

        Bilhete result = bilheteRepository.save(bilhete);
        return result;
    }

    public void salvarListaApostas(List<Aposta> apostas, Bilhete bilhete){
        List<Aposta> listaApostas = new ArrayList<Aposta>();

        apostas.forEach(res -> {
            Aposta aposta = new Aposta();
            aposta.setDataAposta(bilhete.getDataHoraAposta());
            aposta.setNumeroAposta(res.getNumeroAposta());
            aposta.setCodigoModalidade(res.getCodigoModalidade());
            aposta.setModalide(res.getModalide());
            aposta.setCodigoPremio(res.getCodigoPremio());
            aposta.setPremio(res.getPremio());
            aposta.setLoteriaCodigo(res.getLoteriaCodigo());
            aposta.setValorJogo(res.getValorJogo());
            aposta.setBilhete(bilhete);
            aposta.setCodigoBanca(res.getCodigoBanca());
            aposta.setNumeroBilhete(bilhete.getNumeroBilhete());

            listaApostas.add(aposta);
        });

        this.apostaRepository.saveAll(listaApostas);
    }
}
