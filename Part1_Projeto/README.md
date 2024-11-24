# Relatório do projeto de desenvolvimento de uma aplicação móvel para adoção de animais e doação de bens.

## EI/D01/2ºano/Grupo2:
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

# Modelos de Relatório da Aplicação

## **Modelo 1 - Informações sobre Animais**
Este modelo apresenta detalhes completos sobre os animais cadastrados na aplicação.

### **Atributos:**
- `ani_id`: Identificador único do animal.
- `ani_name`: Nome do animal.
- `ani_birthdate`: Data de nascimento.
- `ani_gender`: Gênero (M/F).
- `ani_description`: Descrição detalhada do animal.
- `ani_breed`: Raça.
- `ani_type`: Tipo (Cão, Gato, etc.).
- `ani_image`: URL da imagem.

### **Relacionamentos:**
- Relaciona-se com:
  - **`Status`**: Informações de adoção do animal.
  - **`UA`**: Interações do animal com os usuários.
  - **`Visitas`**: Visitas agendadas para o animal.
  - **`Favorite`**: Animais marcados como favoritos pelos usuários.

---

## **Modelo 2 - Informações do Usuário**
Este modelo armazena os dados dos usuários cadastrados na aplicação, sejam adotantes, doadores ou organizações.

### **Atributos:**
- `usr_id`: Identificador único do usuário.
- `usr_name`: Nome do usuário.
- `usr_email`: E-mail para contato.
- `usr_password`: Senha criptografada.
- `usr_date_registered`: Data de registro na plataforma.
- `usr_image`: URL da foto de perfil do usuário.

### **Relacionamentos:**
- Relaciona-se com:
  - **`Favorite`**: Animais favoritos do usuário.
  - **`Visitas`**: Agendamentos de visitas realizadas pelo usuário.
  - **`Donation`**: Doações realizadas pelo usuário.
  - **`Post` e `Comment`**: Conteúdo gerado e interações com posts.

---

## **Modelo 3 - Status de Adoção**
Este modelo acompanha o progresso da adoção de um animal.

### **Atributos:**
- `sta_id`: Identificador único do status.
- `sta_ani_id`: Identificador do animal.
- `sta_first_visit`: Flag indicando se a primeira visita foi realizada.
- `sta_adopter_evaluation`: Avaliação do adotante.
- `sta_documentation`: Flag indicando se a documentação foi concluída.
- `sta_adaptation_period`: Indica se o animal está em período de adaptação.
- `sta_adoption_confirmed`: Confirmação da adoção.
- `sta_follow_up`: Indica se o acompanhamento pós-adoção está ativo.
- `sta_adopted`: Flag indicando se o animal foi adotado.
- `sta_update_date`: Data da última atualização.
- `sta_event_schedule`: Data/hora de eventos futuros.

### **Relacionamentos:**
- Relaciona-se com:
  - **`Animal`**: O animal associado ao status.
  - **`UAS`**: Ligação entre o status e os usuários envolvidos.

---

## **Modelo 4 - Doações**
Este modelo registra as doações realizadas na plataforma.

### **Atributos:**
- `don_id`: Identificador único da doação.
- `don_amount`: Valor da doação (se for monetária).
- `don_payment_method`: Método de pagamento (MBWay, Cartão de Crédito, etc.).
- `don_date`: Data da doação.
- `don_usr_id`: Identificador do usuário doador.
- `don_typ_name`: Tipo de doação (Financeira, Alimentos, Medicamentos, etc.).

### **Relacionamentos:**
- Relaciona-se com:
  - **`User`**: Quem realizou a doação.
  - **`Loc_Doa`**: Ponto de coleta associado à doação.

---

## **Modelo 5 - Marcações para Visitas**
Este modelo permite agendar visitas para conhecer animais.

### **Atributos:**
- `visit_id`: Identificador único da visita.
- `visit_usr_id`: Identificador do usuário que agendou.
- `visit_ani_id`: Identificador do animal visitado.
- `visit_date`: Data da visita.
- `visit_time`: Hora da visita.
- `visit_status`: Status da visita (ex: *Visita realizada*).

### **Relacionamentos:**
- Relaciona-se com:
  - **`Animal`**: O animal visitado.
  - **`User`**: Quem realizou a visita.

---

## **Modelo 6 - Lista de Favoritos**
Este modelo permite que os usuários marquem animais como favoritos.

### **Atributos:**
- `fav_id`: Identificador único da relação de favorito.
- `fav_usr_id`: Identificador do usuário que marcou o favorito.
- `fav_ani_id`: Identificador do animal marcado como favorito.
- `fav_date`: Data em que foi marcado como favorito.
- `fav_usr_name`: Nome do usuário.
- `fav_ani_name`: Nome do animal.

### **Relacionamentos:**
- Relaciona-se com:
  - **`User`**: Quem marcou o favorito.
  - **`Animal`**: O animal marcado como favorito.

---

## **Modelo 7 - Tabelas de Ligação**

### **1. `UA` (Interações entre Usuário e Animal):**
Rastreia interações como visitas, favoritos e adoções.

#### **Atributos:**
- `ua_id`: Identificador único.
- `ua_usr_id`: Identificador do usuário.
- `ua_ani_id`: Identificador do animal.
- `ua_interaction_type`: Tipo de interação (*Favorited*, *Visited*, *Adopted*, etc.).

---

### **2. `UAS` (Ligação entre User, Animal e Status):**
Registra as etapas do processo de adoção.

#### **Atributos:**
- `uas_id`: Identificador único.
- `uas_usr_id`: Identificador do usuário.
- `uas_ani_id`: Identificador do animal.
- `uas_sta_id`: Identificador do status.
- `uas_stage`: Estágio atual da adoção (*First Visit*, *Adoption Evaluation*, etc.).

---

### **3. `Loc_Doa` (Ligação entre Localização e Doação):**
Rastreia as doações feitas em pontos específicos.

#### **Atributos:**
- `loc_id`: Identificador do ponto de doação.
- `don_id`: Identificador da doação.
- `usr_id`: Identificador do usuário doador.

---

## **Modelo 8 - Post e Comentários**
Permite que usuários compartilhem histórias ou atualizações.

### **Post:**
- `post_id`: Identificador único.
- `post_usr_id`: Identificador do autor.
- `post_content`: Conteúdo do post.
- `post_image_url`: URL da imagem associada.
- `post_date`: Data de criação.
- `post_time`: Hora de criação.
- `post_views`: Contador de visualizações.

---

### **Comentário:**
- `comment_id`: Identificador único.
- `comment_post_id`: Post associado.
- `comment_usr_id`: Autor do comentário.
- `comment_content`: Conteúdo do comentário.
- `comment_date`: Data de criação.
- `comment_time`: Hora de criação.
- `comment_reply_to`: Comentário ao qual este é uma resposta (se aplicável).

---

## **Modelo 9 - Notificações**
Gerencia notificações enviadas para os usuários.

### **Atributos:**
- `notif_id`: Identificador único.
- `notif_usr_id`: Identificador do usuário.
- `notif_type`: Tipo da notificação (*Visit Reminder*, *Adoption Status Update*).
- `notif_description`: Descrição da notificação.
- `notif_date`: Data de envio.
- `notif_time`: Hora de envio.

## 5.Público-Alvo 

O público-alvo da AdoptMe é diversificado, englobando:

  * Pessoas interessadas em adotar animais: Usuários que buscam adotar um animal, com a possibilidade de escolher espécies, raças e características específicas.
  * Doadores: Pessoas que desejam contribuir com a causa, seja por meio de doações em dinheiro ou bens (ração, mantas, brinquedos, etc.).
  * Amantes de animais: Pessoas que desejam acompanhar iniciativas de bem-estar animal e receber atualizações sobre animais resgatados e em adoção.

# **6. Resultados Esperados**

A aplicação **AdoptMe** será uma plataforma inovadora e eficiente que simplifica e otimiza o processo de adoção de animais e doação de bens. Com base nos recursos projetados, espera-se alcançar os seguintes resultados:

## **1. Aumento da Taxa de Adoção de Animais**
- Facilitação do processo de adoção com etapas bem definidas e rastreadas através do sistema de status de adoção.
- Disponibilização de perfis detalhados dos animais (com imagens, descrições e filtros), permitindo que os adotantes encontrem rapidamente o animal que melhor se adequa às suas preferências.
- Maior visibilidade e organização do histórico de interações, como visitas agendadas, avaliações e confirmações de adoção.

## **2. Incentivo à Doação**
- Sistema robusto e transparente para doações, abrangendo tanto bens materiais (ração, brinquedos, medicamentos) quanto contribuições financeiras.
- Integração com pontos de coleta mapeados (tabela `Loc_Doa`), incentivando doações locais e ampliando o alcance da organização.
- Registros detalhados de doações, permitindo que os usuários acompanhem o impacto de suas contribuições.

## **3. Comunicação Eficiente**
- Integração direta com o WhatsApp para facilitar a troca de informações entre os usuários e a organização.
- Envio de notificações personalizadas para lembrar os usuários sobre visitas agendadas, atualizações no status de adoção ou eventos importantes.
- Relatórios claros e atualizados sobre o progresso da adoção, garantindo transparência no processo.

## **4. Engajamento e Comunidade**
- Função de posts e comentários para que usuários compartilhem experiências de adoção, criando uma comunidade ativa e engajada.
- Ferramenta de favoritos para permitir que os usuários acompanhem animais de interesse, aumentando as chances de adoção.

## **5. Eficiência Organizacional**
- Centralização e digitalização dos processos de adoção, doação e agendamento de visitas, reduzindo trabalho manual e erros operacionais.
- Rastreamento de métricas como número de adoções, visitas realizadas e doações recebidas, ajudando na análise de impacto e planejamento estratégico.

Com essas funcionalidades, o **AdoptMe** promete transformar a experiência de adoção e doação, beneficiando animais, adotantes, doadores e a organização.


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

## 10. Contribuições das Unidades Curriculares 

Cada unidade curricular terá a sua contribuição no desenvolvimento deste projeto. Seguindo embaixo as mesmas:

### Programação de Dispositivos Móveis

Esta unidade curricular ajudará na parte de desenvolvimento da nossa app utilizando o Android Studio.

### Programação Orientada a Objetos

Esta unidade curricular ajudará na perte das API's que pretendemos utilizar na app e também no desenvolvimento de um servidor em Spring Boot.

### Bases de dados
Esta unidade curricular ajudará na criação da base de dados da nossa app, o que vai contribuir para uma melhor gestão das informações dos nossos utilizadores e restantes entidades.

### Competências Comunicacionais
Esta unidade curricular contribuirá para a melhoria ou acompanhamento das nossas apresentações no projeto. Podendo entregar, assim, apresentações e relatórios mais perceptíveis para o público.

### Matematica Discreta
Com esta unidade curricular nós pretendemos que ajude na parte de percentagens e análises dos dados da nosssa app. 

## 11. Modelo de Domínio 

Na imagem anexada, pode-se averiguar o nome das entidades e as suas correlações que iremos utilizar na BD (base de dados).

<img width="450" alt="modelo de domínio" src="https://github.com/user-attachments/assets/458d5eaf-c618-40a5-8214-74fd94abc8a3">