
package bytebank_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;

@SpringBootApplication
public class BytebankApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BytebankApiApplication.class, args);
    }
}

@Service
class ContaService {

    private BigDecimal saldo = new BigDecimal("1000.00");

    public synchronized BigDecimal consultarSaldo() {
        return saldo;
    }

    public synchronized BigDecimal depositar(BigDecimal valor) {
        validarValor(valor);
        saldo = saldo.add(valor);
        return saldo;
    }

    public synchronized BigDecimal sacar(BigDecimal valor) {
        validarValor(valor);

        if (valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        saldo = saldo.subtract(valor);
        return saldo;
    }

    private void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor invalido");
        }
    }
}

@RestController
@RequestMapping("/api/conta")
class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/saldo")
    public Map<String, BigDecimal> saldo() {
        return Map.of("saldo", contaService.consultarSaldo());
    }

    @PostMapping("/depositar")
    public Map<String, BigDecimal> depositar(@RequestBody ValorRequest request) {
        return Map.of("saldo", contaService.depositar(request.valor()));
    }

    @PostMapping("/sacar")
    public Map<String, BigDecimal> sacar(@RequestBody ValorRequest request) {
        return Map.of("saldo", contaService.sacar(request.valor()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> tratarErro(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(Map.of("erro", e.getMessage()));
    }
}

record ValorRequest(BigDecimal valor) {}
