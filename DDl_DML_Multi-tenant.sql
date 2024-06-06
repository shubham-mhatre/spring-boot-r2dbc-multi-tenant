

-- Dumping database structure for tenant_1
DROP DATABASE IF EXISTS `tenant_1`;
CREATE DATABASE IF NOT EXISTS `tenant_1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tenant_1`;

-- Dumping structure for table tenant_1.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `tax_rate` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table tenant_1.product: ~2 rows (approximately)
INSERT INTO `product` (`id`, `product_name`, `quantity`, `tax_rate`, `total_price`) VALUES
	(1, 'TV', 2, 6, 120000),
	(2, 'Laptop', 1, 3, 130000);


-- Dumping database structure for tenant_2
DROP DATABASE IF EXISTS `tenant_2`;
CREATE DATABASE IF NOT EXISTS `tenant_2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tenant_2`;

-- Dumping structure for table tenant_2.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `tax_rate` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table tenant_2.product: ~2 rows (approximately)
INSERT INTO `product` (`id`, `product_name`, `quantity`, `tax_rate`, `total_price`) VALUES
	(1, 'Mobile', 1, 5, 47000),
	(2, 'Headphones', 2, 3, 20000),
	(3, 'Keyboard & mouse', 4, 7, 23000);


-- Dumping database structure for master_db_tenant
DROP DATABASE IF EXISTS `master_db_tenant`;
CREATE DATABASE IF NOT EXISTS `master_db_tenant` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `master_db_tenant`;

-- Dumping structure for table master_db_tenant.tenant_master
DROP TABLE IF EXISTS `tenant_master`;
CREATE TABLE IF NOT EXISTS `tenant_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(50) NOT NULL DEFAULT '0',
  `db_name` varchar(50) NOT NULL DEFAULT '0',
  `url` varchar(255) NOT NULL DEFAULT '0',
  `username` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  `is_active` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table master_db_tenant.tenant_master: ~2 rows (approximately)
INSERT INTO `tenant_master` (`id`, `tenant_id`, `db_name`, `url`, `username`, `password`, `is_active`) VALUES
	(1, 'tenant_1', 'tenant_1', 'r2dbc:mysql://localhost:3306/tenant_1', 'root', 'root', 1),
	(2, 'tenant_2', 'tenant_2', 'r2dbc:mysql://localhost:3306/tenant_2', 'root', 'root', 1);

