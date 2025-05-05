CREATE TABLE IF NOT EXISTS cars_movies (
                                                  id CHAR(36) NOT NULL DEFAULT (UUID()) PRIMARY KEY,
                                                  car_movie_name VARCHAR(255) NOT NULL,
                                                  car_movie_year CHAR(4) NOT NULL,
                                                  duration INT NOT NULL
);