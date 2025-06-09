# Versão código comentado
# 💼 Sistema PDV (Ponto de Venda) — Estudo em Java

Este projeto é um estudo prático voltado para a construção de um **sistema PDV (Ponto de Venda)** com base em princípios sólidos da **programação orientada a objetos**. Desenvolvido com foco em boas práticas como **inversão de controle**, **injeção de dependência**, **baixo acoplamento** e **alta coesão**, o sistema simula operações de cadastro de produtos, controle de estoque e processo de compra com emissão de nota fiscal.

---

## ✨ Funcionalidades

- 📦 **Cadastro de produtos**: alimentos, produtos de limpeza e utilitários  
- 🏷️ **Geração automática de código de barras**  
- 📅 **Data de validade automática** para alimentos (1 ano a partir da fabricação)  
- 💰 **Simulação de compra** com desconto ou acréscimo baseado no método de pagamento  
- 🧾 **Geração de nota fiscal** com valor total, impostos e data da compra  
- 🔐 **Sistema de acesso** com menus distintos para administradores e clientes  
- 🧮 **Tratamento robusto de entrada** de dados com mensagens de erro amigáveis  

---

## 🧠 Conceitos aplicados

- ✅ Inversão de Controle (IoC)  
- ✅ Injeção de Dependência  
- ✅ Polimorfismo e Herança  
- ✅ Interfaces e abstração de serviços  
- ✅ Encapsulamento e organização modular  
- ✅ Alto nível de coesão e baixo acoplamento  

---

## 📁 Estrutura do Projeto

```
src/
├── aplicacao/            # Classe principal (Programa.java)
├── entidades/            # Modelos de produto e nota fiscal
│   ├── Produto.java
│   ├── ProdAlimenticio.java
│   ├── ProdLimpeza.java
│   ├── ProdUtilitario.java
│   └── NotaFiscal.java
├── servicos/             # Serviços de pagamento e regras de negócio
│   ├── ServicoMetodoPagamento.java
│   ├── ServicoMetodoPagamentoPix.java
│   ├── ServicoMetodoPagamentoDebito.java
│   ├── ServicoMetodoPagamentoCredito.java
│   └── ServicoPagamento.java
```

---

## 🚀 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repo.git
   cd nome-do-repo
   ```

2. Importe o projeto em uma IDE Java (Eclipse, IntelliJ, VS Code...) ou compile manualmente com:
   ```bash
   javac aplicacao/Programa.java
   java aplicacao.Programa
   ```

---

## 🔐 Senha do Administrador

- Para acessar o **modo administrador**, digite `1234` ao iniciar o sistema.  
- Qualquer outro valor acessa o **modo cliente**.

---

## 🛠 Requisitos

- Java 11 ou superior  
- Nenhuma dependência externa (projeto 100% Java puro)  

---

## 🧑‍💻 Autor
Rafael Brito Valadares - Eng. Software Ucsal
Desenvolvido como parte dos estudos em Programação Orientada a Objetos com foco em design limpo e manutenção de código.

---

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
