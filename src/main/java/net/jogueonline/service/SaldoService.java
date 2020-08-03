package net.jogueonline.service;

import net.jogueonline.domain.*;
import net.jogueonline.domain.enumeration.TipoMovimento;
import net.jogueonline.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class SaldoService {
    private final Logger log = LoggerFactory.getLogger(BilheteService.class);
    private final BancaRepository bancaRepository;
    private final RevendedorRepository revendedorRepository;
    private final SaldoRepository saldoRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final TerminalRepository terminalRepository;
   public SaldoService(BancaRepository bancaRepository, RevendedorRepository revendedorRepository, SaldoRepository saldoRepository, MovimentacaoRepository movimentacaoRepository, TerminalRepository terminalRepository){
       this.bancaRepository = bancaRepository;
       this.revendedorRepository = revendedorRepository;
       this.saldoRepository = saldoRepository;
       this.movimentacaoRepository = movimentacaoRepository;
       this.terminalRepository = terminalRepository;
   }

    public void atualizaSaldo(Bilhete bilhete){

        Terminal terminal = terminalRepository.getOne(bilhete.getCodigoTerminal());
        Banca banca = bancaRepository.getOne(bilhete.getBanca().getId());
        Movimentacao movimentacaoBanca = new Movimentacao();
        Movimentacao movimentacaoRevendedor = new Movimentacao();

        BigDecimal saldoRevendedor;
        BigDecimal saldoBanca;

        Revendedor revendedor = terminal.getRevendedor();

        saldoRevendedor = revendedor.getSaldo().getValor();
        saldoBanca = banca.getSaldo().getValor();

        if(verificaSaldo(bilhete.getValorBilhete(),saldoRevendedor)){
            revendedor.getSaldo().setValor(saldoRevendedor.subtract(bilhete.getValorBilhete()));
            banca.getSaldo().setValor(saldoBanca.add(bilhete.getValorBilhete()));

            movimentacaoRevendedor.setValor(bilhete.getValorBilhete());
            movimentacaoRevendedor.setSaldo(revendedor.getSaldo());
            movimentacaoRevendedor.setTipoMovimento(TipoMovimento.SAIDA);
            movimentacaoRepository.save(movimentacaoRevendedor);
            revendedorRepository.save(revendedor);

            movimentacaoBanca.setValor(bilhete.getValorBilhete());
            movimentacaoBanca.setSaldo(banca.getSaldo());
            movimentacaoBanca.setTipoMovimento(TipoMovimento.ENTRADA);
            movimentacaoRepository.save(movimentacaoBanca);
            bancaRepository.save(banca);
        }
    }
    public boolean verificaSaldo(BigDecimal valor, BigDecimal saldoRevendedor){
        if(valor.compareTo(saldoRevendedor) > 0) {
            log.debug("VALOR MAIOR !!!!!!!");
            return false;
        }else {
            log.debug("VALOR MENOR !!!!!!!");
            return true;
        }
    }
}

