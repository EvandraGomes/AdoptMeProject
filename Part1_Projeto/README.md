# Relatório do projeto de desenvolvimento de uma aplicação móvel para adoção de animais e doação de bens.

##EI/D01/2ºano/Grupo2:
1. Evandra Gomes 20230416
2. Silana Naval 20230306
3. Wesley Vinhas 20220119

Lisboa, aos 20/10/2024

**Universidade Europeia-IADE**

## 1.Introdução 

O projeto AdoptMe é uma aplicação móvel voltada para facilitar o processo de
adoção de animais e a doação de bens para os animais a organização. A ideia
surgiu da necessidade de tornar o processo de adoção mais acessível, ágil e
organizado, ao mesmo tempo que incentiva e facilita as doações de itens
essenciais para o bem-estar dos animais (como ração, brinquedos, mantas e
medicamentos).


## 2.Contexto e Justificativa 

O aumento do número de animais abandonados e a falta de recursos para cuidar
deles são problemas que afetam diversas cidades e comunidades em Portugal.
Muitas ONGs e abrigos dependem exclusivamente de doações para manter seus
serviços. Entretanto, a ausência de plataformas centralizadas, fáceis de usar e
bem estruturadas para adoção e doação dificulta esse processo.
Além disso, o crescimento do uso de smartphones e o hábito das pessoas de
utilizarem aplicativos para realizar tarefas diárias indicam que uma solução digital
é uma resposta apropriada a este problema. Aplicações móveis são uma
ferramenta poderosa para engajar o público e promover causas como a adoção de
animais, proporcionando uma plataforma com boa visibilidade, comunicação
direta e acessibilidade.

## 3.Objetivos do Projeto

**O projeto AdoptMe tem como objetivos principais:**

   * **Facilitar a adoção de animais:** Proporcionar uma interface intuitiva e completa onde os usuários possam visualizar, buscar e adotar animais de forma organizada e prática.

  * **Incentivar e facilitar doações:** Permitir que os usuários façam doações de bens com transparência e facilidade.

  * **Promover a adoção responsável:** Garantir que o processo de adoção seja rastreado e acompanhado, fornecendo atualizações sobre o estado de saúde e vacinação dos animais.

  * **Fortalecer a comunicação entre a organização e adotantes/doadores:** A aplicação fornecerá um canal direto de comunicação, com recursos como marcações via WhatsApp e notificações sobre o processo de adoção.

  * **Oferecer uma solução acessível:** Com uma plataforma digital, a ideia é alcançar um público maior, facilitando tanto as adoções quanto as doações em qualquer lugar.

## 4.Funcionalidades da Aplicação

**Modelo 1- Inofrmações do animal** Este modelo armazena as informações básicas sobre os animais disponíveis para adoção, incluindo:

  * Nome do animal
  * Espécie (cão, gato, etc.)
  * Raça
  * Idade
  * Cor
  * Descrição detalhada
  * Imagens do animal

  Essas informações serão usadas para apresentar os animais de forma clara aos possíveis adotantes, com filtros de busca e categorias.

**Modelo 2 - Informação do Usuário** Esse modelo armazena dados do perfil dos usuários que se registam na aplicação, sejam adotantes ou doadores:

  * Nome
  * Foto de perfil
  * Informações de contato (telefone, e-mail)
  * Localização
  * Histórico de adoções (caso o usuário já tenha adotado antes)
  * Preferências de adoção (espécie, raça, idade desejada)

**Modelo 3 - Status de Adoção** Este modelo acompanha o status de cada adoção:

  * Atualizações de datas importantes (data de adoção, vacinação, etc.)
  * Rastreamento do processo (por exemplo, se o animal já foi adotado ou vacinado)
  * Comentários dos adotantes ou da organização sobre o processo de adoção

**Modelo 4 - Doações** Armazena as informações sobre as doações feitas pelos usuários:
  * Tipos de doação (ração, brinquedos, mantas, medicamentos)
  * Valor de doação (no caso de doações em dinheiro)
  * Perfil do doador
  * Histórico de doações realizadas

**Modelo 5 - Marcações** Esta funcionalidade permite que os usuários agendem visitas ou encontros com a organização para adoção:

  * Data e hora da marcação
  * Link direto para o WhatsApp, facilitando a comunicação com a organização
  * Notificações para lembrar os usuários sobre datas e horários agendados

**Modelo 6 - Lista de Favoritos** Este modelo permite que os usuários criem uma lista de animais favoritos, para que possam acompanhar aqueles que mais lhes interessam:
  * Adição de animais à lista de favoritos
  * Visualização rápida dos animais favoritos em uma aba separada

## 5.Público-Alvo 

O público-alvo da AdoptMe é diversificado, englobando:

  * Pessoas interessadas em adotar animais: Usuários que buscam adotar um animal, com a possibilidade de escolher espécies, raças e características específicas.
  * Doadores: Pessoas que desejam contribuir com a causa, seja por meio de doações em dinheiro ou bens (ração, mantas, brinquedos, etc.).
  * Amantes de animais: Pessoas que desejam acompanhar iniciativas de bem-estar animal e receber atualizações sobre animais resgatados e em adoção.

## 6.Resultados esperados 

A aplicação **AdoptMe** será uma plataforma robusta que
facilitará o processo de adoção de animais e doação de bens. Espera-se que o sistema permita:

  * Aumento da taxa de adoção de animais, tornando o processo mais acessível e organizado
  * Incentivo à doação, com um sistema simples e transparente
  * Melhor comunicação entre adotantes/doadores e a organização,através da integração com WhatsApp e notificações.


## 8. Aplicações/plataformas semelhantes já existentes (em Portugal):

Sim, em Portugal já existem algumas plataformas voltadas para a adoção de animais que funcionam de maneira semelhante ao conceito da AdoptMe. Algumas delas são:

  1. **Pinder–** Inspirado pelo conceito de "match" semelhante ao Tinder, o Pinder é uma plataforma que conecta pessoas interessadas em adotar animais com tutores ou criadores certificados. Ela também promove a criação de uma comunidade de amantes de animais, onde os utilizadores podem partilhar interesses e discutir o bem-estar animal
  
  2. **Adopta-me.org–** Esta é uma plataforma mais tradicional e conhecida em Portugal que oferece funcionalidades de busca e adoção de animais. Os utilizadores podem filtrar animais por espécie, raça, idade e localização, facilitando o processo de adoção em diversas regiões do país
  
  3. **Petify–** Além de promover a adoção de animais, o Petify oferece recursos para encontrar animais perdidos. A plataforma usa geolocalização para facilitar o contato entre pessoas interessadas em adotar ou que tenham perdido seus animais

Essas plataformas compartilham o mesmo propósito de facilitar o processo de adoção e melhorar a conexão entre adotantes e ONGs, além de promover a esponsabilidade social em relação ao bem-estar animal.

## 9.Guiões de teste/Utilizador

**Caso de Utilização Core:** Adoção de um Animal

**Descrição:** Este é o caso central da aplicação, onde o utilizador percorre o processo de adoção de um animal.

**Passos:**

  **1. Abertura da App:** O utilizador (Evandra) faz login ou registo na aplicação pela primeira vez.
  
  **2. Pesquisa de Animais:** Evandra usa a funcionalidade de pesquisa, aplicando filtros de preferência (espécie: cão, idade: filhote, localização: Lisboa).
  
  **3. Exploração:** A app exibe uma lista de animais disponíveis para adoção, com informações detalhadas como raça, idade, características e fotos.
  
  **4. Adicionar aos Favoritos:** Evandra encontra um cão do qual gosta e adiciona-o à sua lista de favoritos.
  
  **5. Agendamento de Visita:** Após refletir, Evandra decide marcar uma visita ao abrigo através do sistema de agendamento da app. Ela escolhe um horário e recebe um link para contacto via WhatsApp.
  
  **6. Notificações:** A app envia notificações para lembrar Evandra da data e hora agendadas.
  
  **7. Finalização do Processo:** Depois de visitar o abrigo e confirmar a adoção, a app atualiza o estado do animal, marcando-o como "adotado" no perfil de Evandra e fornecendo atualizações futuras sobre vacinação e saúde.


### **Caso de Utilização 2:** Doação de Bens

**Descrição:** Aqui, o utilizador utiliza a app para doar itens essenciais para os animais.

**Passos:**
  
  **1. Navegação para Secção de Doações:** Wesley, um utilizador, faz login e
  escolhe a opção de "Doar" no menu principal.
 
  **2. Escolha dos Itens:** Wesley seleciona os tipos de doação que pretende
  fazer (ração e medicamentos).
 
  **3. Seleção de ponto de entrega/recolha:** A app mostra-lhe uma lista com
  pontos de entrega e recolha próximos e ele seleciona um.
 
  **4. Finalização da Doação:** Wesley conclui o processo, recebendo uma
  confirmação no e-mail e notificações na app sobre a doação e o seu impacto.
 
  **5. Histórico de Doações:** Wesley acede à sua secção de histórico para acompanhar as doações feitas ao longo do tempo.


### **Caso de Utilização 3:** Acompanhamento do Estado de Adoção

**Descrição:** Este caso foca-se em utilizadores que já adotaram um animal e
desejam acompanhar o seu progresso.

**Passos:**

  1. Login e Acesso ao Perfil do Animal: Silana, que adotou um gato pela app,
  faz login e acede à secção "Adoções".
  
  2. Ver Atualizações de Saúde: A app mostra as últimas atualizações sobre o
  estado de saúde do gato, incluindo datas de vacinação, check-ups e outros
  cuidados veterinários.
  
  3. Notificações de Lembretes: A app envia notificações automáticas para
  lembrar Silana das datas importantes relacionadas com os cuidados do
  gato.
  
  4. Envio de Mensagens para o Abrigo: Silana utiliza o sistema de mensagens
  integrado na app para contactar o abrigo e esclarecer dúvidas sobre o
  estado de saúde do animal.


