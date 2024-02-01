/* populate tables */
/* users */
INSERT INTO users (first_name, last_name, username, password, is_active) VALUES
  ('John', 'Doe', 'john.doe', 'SecurePass1', true),
  ('Jane', 'Smith', 'jane.smith', 'StrongPwd123', true),
  ('Alice', 'Johnson', 'alice.johnson', 'Passw0rd123!', true),
  ('Bob', 'Williams', 'bob.williams', 'SecretPass12', false),
  ('Emily', 'Brown', 'emily.brown', 'SecurePassword', true),
  ('Michael', 'Davis', 'michael.davis', 'MyPwd12345!', true),
  ('Olivia', 'Garcia', 'olivia.garcia', 'SuperSecure1', false),
  ('David', 'Martinez', 'david.martinez', 'P@ssword123', true),
  ('Sophia', 'Rodriguez', 'sophia.rodriguez', 'StrongPass1234', false),
  ('William', 'Hernandez', 'william.hernandez', 'MySecurePassword', true);

/* trainees */
INSERT INTO trainees (date_of_birth, address, user_id)
VALUES
    ('1990-05-15', '123 Main St', 1),
    ('1988-09-21', '456 Elm St', 2),
    ('1995-03-08', '789 Oak St', 3),
    ('1992-11-12', '101 Pine St', 4),
    ('1987-07-25', '210 Maple St', 5),
    ('1998-02-17', '333 Cedar St', 6),
    ('1991-06-30', '777 Birch St', 7);


/* training types */
INSERT INTO training_types (training_type_name) VALUES
    ('Cardio Workout'),
    ('Strength Training'),
    ('HIIT (High-Intensity Interval Training)'),
    ('CrossFit'),
    ('Yoga Session'),
    ('Pilates Class'),
    ('Spinning Class'),
    ('Zumba Fitness'),
    ('Functional Training'),
    ('Bodyweight Exercise Workshop');


/* trainers */
INSERT INTO trainers (specialization, user_id)
VALUES
    (1, 8),
    (2, 9),
    (3, 10);

/* trainings */
INSERT INTO trainings (trainee_id, trainer_id, training_name, training_type_id, training_date, training_duration)
VALUES
    (1, 1, 'Training 1', 1, '2023-01-10', 60),
    (2, 2, 'Training 2', 2, '2023-01-11', 90),
    (3, 3, 'Training 3', 3, '2023-01-12', 45),
    (4, 1, 'Training 4', 1, '2023-01-13', 75),
    (5, 2, 'Training 5', 2, '2023-01-14', 60),
    (6, 3, 'Training 6', 3, '2023-01-15', 60),
    (7, 1, 'Training 7', 1, '2023-01-16', 45),
    (1, 2, 'Training 8', 2, '2023-01-17', 60),
    (2, 3, 'Training 9', 3, '2023-01-18', 75),
    (3, 1, 'Training 10', 1, '2023-01-19', 90);
