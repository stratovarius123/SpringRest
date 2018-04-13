-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 11-Abr-2018 às 04:08
-- Versão do servidor: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring_dev`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `categoria` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id`, `categoria`) VALUES
(1, 'Informatica'),
(2, 'Escritorio');

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria_produto`
--

CREATE TABLE `categoria_produto` (
  `produto_id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `categoria_produto`
--

INSERT INTO `categoria_produto` (`produto_id`, `categoria_id`) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

CREATE TABLE `cidade` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES
(1, 'Uberlandia', 1),
(2, 'Sao Paulo', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `cpf_ou_cnpj` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id`, `cpf_ou_cnpj`, `email`, `nome`, `tipo`) VALUES
(1, '417033258010', 'Maria@gmail.com', 'Maria Silva', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `id` int(11) NOT NULL,
  `bairro` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cep` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `complemento` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `logradouro` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `numero` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cidade_id` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`id`, `bairro`, `cep`, `complemento`, `logradouro`, `numero`, `cidade_id`, `cliente_id`) VALUES
(1, 'jardim', '38220834', 'apto. 303', 'Rua Flores', '300', 1, 1),
(2, 'Centro', '38777012', 'Sala 800', 'Av. Matos', '105', 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado`
--

CREATE TABLE `estado` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `estado`
--

INSERT INTO `estado` (`id`, `nome`) VALUES
(1, 'Minas Gerais'),
(2, 'São Paulo');

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_pedido`
--

CREATE TABLE `item_pedido` (
  `desconto` double NOT NULL,
  `preco` double NOT NULL,
  `quantidade` double NOT NULL,
  `pedido_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `item_pedido`
--

INSERT INTO `item_pedido` (`desconto`, `preco`, `quantidade`, `pedido_id`, `produto_id`) VALUES
(0, 2000, 1, 1, 1),
(0, 80, 2, 1, 3),
(100, 800, 1, 2, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

CREATE TABLE `pagamento` (
  `pedido_id` int(11) NOT NULL,
  `estado_pagamento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `pagamento`
--

INSERT INTO `pagamento` (`pedido_id`, `estado_pagamento`) VALUES
(1, 2),
(2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento_com_boleto`
--

CREATE TABLE `pagamento_com_boleto` (
  `data_pagamento` datetime DEFAULT NULL,
  `data_vencimento` datetime DEFAULT NULL,
  `pedido_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `pagamento_com_boleto`
--

INSERT INTO `pagamento_com_boleto` (`data_pagamento`, `data_vencimento`, `pedido_id`) VALUES
('2017-10-20 00:00:00', NULL, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento_com_cartao`
--

CREATE TABLE `pagamento_com_cartao` (
  `numero_de_parcelas` int(11) DEFAULT NULL,
  `pedido_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `pagamento_com_cartao`
--

INSERT INTO `pagamento_com_cartao` (`numero_de_parcelas`, `pedido_id`) VALUES
(6, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `data` datetime DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `endereco_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `pedido`
--

INSERT INTO `pedido` (`id`, `data`, `cliente_id`, `endereco_id`) VALUES
(1, '2017-09-30 10:32:00', 1, 1),
(2, '2017-10-10 19:35:00', 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `preco` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id`, `nome`, `preco`) VALUES
(1, 'Computador', 2000),
(2, 'Impressora', 800),
(3, 'Mouse', 80);

-- --------------------------------------------------------

--
-- Estrutura da tabela `telefone`
--

CREATE TABLE `telefone` (
  `cliente_id` int(11) NOT NULL,
  `telefones` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `telefone`
--

INSERT INTO `telefone` (`cliente_id`, `telefones`) VALUES
(1, '35967612'),
(1, '996112374');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categoria_produto`
--
ALTER TABLE `categoria_produto`
  ADD KEY `FKhs0w91lfxf2oyx2602gvhlu88` (`categoria_id`),
  ADD KEY `FK3ew6ukf9clnmigm07tvtx4b6o` (`produto_id`);

--
-- Indexes for table `cidade`
--
ALTER TABLE `cidade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8b1kcb3wucapb8dejshyn5fsx` (`cidade_id`),
  ADD KEY `FK8s7ivtl4foyhrfam9xqom73n9` (`cliente_id`);

--
-- Indexes for table `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD PRIMARY KEY (`pedido_id`,`produto_id`),
  ADD KEY `FKtk55mn6d6bvl5h0no5uagi3sf` (`produto_id`);

--
-- Indexes for table `pagamento`
--
ALTER TABLE `pagamento`
  ADD PRIMARY KEY (`pedido_id`);

--
-- Indexes for table `pagamento_com_boleto`
--
ALTER TABLE `pagamento_com_boleto`
  ADD PRIMARY KEY (`pedido_id`);

--
-- Indexes for table `pagamento_com_cartao`
--
ALTER TABLE `pagamento_com_cartao`
  ADD PRIMARY KEY (`pedido_id`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id`),
  ADD KEY `FKsot2og0lvhm2t4kuu9s2obef3` (`endereco_id`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `telefone`
--
ALTER TABLE `telefone`
  ADD KEY `FK8aafha0njkoyoe3kvrwsy3g8u` (`cliente_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cidade`
--
ALTER TABLE `cidade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `estado`
--
ALTER TABLE `estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `categoria_produto`
--
ALTER TABLE `categoria_produto`
  ADD CONSTRAINT `FK3ew6ukf9clnmigm07tvtx4b6o` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`),
  ADD CONSTRAINT `FKhs0w91lfxf2oyx2602gvhlu88` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

--
-- Limitadores para a tabela `cidade`
--
ALTER TABLE `cidade`
  ADD CONSTRAINT `FKkworrwk40xj58kevvh3evi500` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`);

--
-- Limitadores para a tabela `endereco`
--
ALTER TABLE `endereco`
  ADD CONSTRAINT `FK8b1kcb3wucapb8dejshyn5fsx` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`),
  ADD CONSTRAINT `FK8s7ivtl4foyhrfam9xqom73n9` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Limitadores para a tabela `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `FKtk55mn6d6bvl5h0no5uagi3sf` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`);

--
-- Limitadores para a tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD CONSTRAINT `FKthad9tkw4188hb3qo1lm5ueb0` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`);

--
-- Limitadores para a tabela `pagamento_com_boleto`
--
ALTER TABLE `pagamento_com_boleto`
  ADD CONSTRAINT `FKcr74vrxf8nfph0knq2bho8doo` FOREIGN KEY (`pedido_id`) REFERENCES `pagamento` (`pedido_id`);

--
-- Limitadores para a tabela `pagamento_com_cartao`
--
ALTER TABLE `pagamento_com_cartao`
  ADD CONSTRAINT `FKta3cdnuuxclwfh52t4qi432ow` FOREIGN KEY (`pedido_id`) REFERENCES `pagamento` (`pedido_id`);

--
-- Limitadores para a tabela `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK30s8j2ktpay6of18lbyqn3632` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FKsot2og0lvhm2t4kuu9s2obef3` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`);

--
-- Limitadores para a tabela `telefone`
--
ALTER TABLE `telefone`
  ADD CONSTRAINT `FK8aafha0njkoyoe3kvrwsy3g8u` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
