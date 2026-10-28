# Autorizacoes dos controllers

As expressoes estao no nivel da classe e se aplicam a todos os endpoints de cada rota. `hasRole` e `hasAnyRole` comparam as authorities `ROLE_*` emitidas no JWT. As regras por URL de `ResourceServerConfig` continuam ativas como uma segunda camada de protecao.

Excecao: `POST /cadastros-iniciais/login` e publico para permitir autenticacao; o cadastro (`POST /cadastros-iniciais`) exige usuario autenticado.

| Controller | Rota | Acesso exigido |
| --- | --- | --- |
| `FuncionarioController` | `/funcionarios/**` | `ROLE_ADMIN` |
| `RoleController` | `/roles/**` | `ROLE_ADMIN` |
| `ConsultaController` | `/consultas/**` | `ROLE_ADMIN` ou `ROLE_MEDICO` |
| `PrescricaoController` | `/prescricoes/**` | `ROLE_ADMIN` ou `ROLE_MEDICO` |
| `PrescricaoItemController` | `/prescricao-itens/**` | `ROLE_ADMIN` ou `ROLE_MEDICO` |
| `AdministracaoMedicamentoController` | `/administracoes-medicamentos/**` | `ROLE_ADMIN`, `ROLE_MEDICO`, `ROLE_ENFERMEIRO` ou `ROLE_TECNICO_ENFERMAGEM` |
| `SinaisVitaisController` | `/sinais-vitais/**` | `ROLE_ADMIN`, `ROLE_MEDICO`, `ROLE_ENFERMEIRO` ou `ROLE_TECNICO_ENFERMAGEM` |
| `PacienteController` | `/pacientes/**` | `ROLE_ADMIN`, `ROLE_MEDICO`, `ROLE_ENFERMEIRO` ou `ROLE_TECNICO_ENFERMAGEM` |
| `ProntuarioController` | `/prontuarios/**` | `ROLE_ADMIN`, `ROLE_MEDICO`, `ROLE_ENFERMEIRO` ou `ROLE_TECNICO_ENFERMAGEM` |
| `ProntuarioRegistroController` | `/prontuario-registros/**` | `ROLE_ADMIN`, `ROLE_MEDICO`, `ROLE_ENFERMEIRO` ou `ROLE_TECNICO_ENFERMAGEM` |
| `InternacaoController` | `/internacoes/**` | `ROLE_ADMIN`, `ROLE_MEDICO`, `ROLE_ENFERMEIRO` ou `ROLE_TECNICO_ENFERMAGEM` |
| `CadastroInicialController` | `POST /cadastros-iniciais` | Usuario autenticado |
| `CadastroInicialController` | `POST /cadastros-iniciais/login` | Publico |
| `LeitoController` | `/leitos/**` | Usuario autenticado |
| `QuartoController` | `/quartos/**` | Usuario autenticado |
