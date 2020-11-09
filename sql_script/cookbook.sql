create database cookbook;

CREATE TABLE users(
	id int PRIMARY KEY auto_increment,
	user_id varchar(16) unique NOT NULL,
	password varchar(16) NOT NULL,
	nickname varchar(20) NOT null
);

insert into users
values
(default, "admin123", "admin123", "admin123");

create table recipes(
    id int primary key auto_increment,
    recipe_name varchar(50) not null,
    prep_time int not null,
    serve int not null,
    cook_time int not null,
    picPath varchar(60) not null,
    instructions varchar(500)
);

insert into recipes
values
(default, "Hong Shao Rou---Red Braised Pork Belly", 10, 4, 1, "./recipe_pic/hongshaorou.jpg",
"Clean and cut the pork belly into cubes around 2 inches long.$Boil a large pot of water, add 2 slices of ginger and 2 green onions, cook the pork belly for around 4 minutes. Transfer out and wash with warm water. Set aside and drain.$Heat up wok on medium fire; brush some oil on the bottom. Sautee the pork belly until the surface becomes slightly brown. Transfer the pork cubes out to a pre-heat clay pot with green onion and ginger slices laid in bottle or a plate and leave the oil in.");


create table ingredients (
    id int primary key auto_increment,
    name varchar(30) not null,
    amount varchar(15) not null,
    action varchar(100),
    recipe_id int,
    constraint fk_id foreign key(recipe_id) references recipes(id)
);

insert into ingredients (id, amount, name, action, recipe_id)
values
(default, "500g", "pork belly", "cut into cubes around 2 inches", 1),
(default, "4 tablespoons", "light soy sauce", "", 1),
(default, "2 tablespoons", "brown sugar", "broken if u have large pieces", 1),
(default, "2 inches", "ginger", "cut into slices", 1),
(default, "4","green onions", "1 finely chopped for garnish and the left into long sections", 1),
(default, "1 cup", "hot water", "", 1),
(default, "", "oil", "for brushing (optional if you are using iron wok)", 1);

