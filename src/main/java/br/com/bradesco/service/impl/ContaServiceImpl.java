package br.com.bradesco.service.impl;

import br.com.bradesco.Client.UsuarioClient;
import br.com.bradesco.domain.entity.Conta;
import br.com.bradesco.domain.mappers.ContaMapper;
import br.com.bradesco.domain.payload.request.ContaRequest;
import br.com.bradesco.domain.payload.response.ContaResponse;
import br.com.bradesco.domain.payload.response.UsuarioResponse;
import br.com.bradesco.exceptions.ContaNotFoundException;
import br.com.bradesco.exceptions.UsuarioNotFoundException;
import br.com.bradesco.repository.ContaRepository;
import br.com.bradesco.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;
    private final UsuarioClient usuarioClient;


    public ContaResponse createConta(final ContaRequest contaRequest) {

        try{
            UsuarioResponse usuarioResponse = usuarioClient.getUsuarioById(contaRequest.getIdUsuario());
            contaRequest.setIdUsuario(usuarioResponse.getIdUsuario());
            return contaMapper.toResponse(contaRepository.save(contaMapper.toEntity(contaRequest)));
        }catch(Exception ex){
            throw new UsuarioNotFoundException();
        }

    }

    public ContaResponse updateSaldoConta(final UUID idConta, final BigDecimal saldo) {
        Conta conta = getConta(idConta);
        conta.setSaldo(saldo);
        contaMapper.toUpdateEntity(null, conta, saldo);
        return contaMapper.toResponse(contaRepository.save(conta));
    }

    public List<ContaResponse> getAllConta() {
        List<Conta> contas = contaRepository.findAll();
        return contas.stream().map(contaMapper::toResponse).toList();
    }

    public ContaResponse getContaById(final UUID idConta) {
        return contaMapper.toResponse(getConta(idConta));
    }

    private Conta getConta(UUID idConta) {
        return contaRepository.findById(idConta).orElseThrow(ContaNotFoundException::new);
    }
}
