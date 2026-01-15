INSERT IGNORE INTO users (username, password, enabled)
    VALUES  ('ivan', '$2a$10$8.UnVuG9HHgffUDAlk8qfOZ0tAJlnRgAfJCXnuKNRc00PBwIF/V/q', true),
            ('pesho', '$2a$10$8.UnVuG9HHgffUDAlk8qfOZ0tAJlnRgAfJCXnuKNRc00PBwIF/V/q', true);

INSERT IGNORE INTO authorities (username, authority)
    VALUES  ('ivan', 'ROLE_USER'),
            ('pesho', 'ROLE_ADMIN');