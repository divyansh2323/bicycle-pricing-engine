CREATE TABLE parts (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  category VARCHAR(255),
  price DECIMAL(19,4) NOT NULL
);

CREATE TABLE price_history (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  part_id BIGINT NOT NULL,
  old_price DECIMAL(19,4),
  new_price DECIMAL(19,4),
  changed_at DATETIME(3) NOT NULL,
  CONSTRAINT fk_price_history_part FOREIGN KEY (part_id) REFERENCES parts(id)
);

CREATE TABLE cycle_configurations (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE configuration_parts (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  configuration_id BIGINT NOT NULL,
  part_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  CONSTRAINT fk_configuration_part FOREIGN KEY (configuration_id) REFERENCES cycle_configurations(id),
  CONSTRAINT fk_configuration_part_part FOREIGN KEY (part_id) REFERENCES parts(id)
);
