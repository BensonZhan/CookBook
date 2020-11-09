create database if not exists cookbook;

use cookbook;

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


insert into recipes
values
(default, "Cong Bao Yang Rou---Cumin Lamb ", 10, 4, 1, "./recipe_pic/congbaoyangrou.jpg",
" Soak the lamb in warm water for half an hour, then wash and cut into thick shreds.$Add starch and light soy sauce to the cut mutton, then add cumin grains and marinate. Pick and wash coriander and set aside. Cook with oil, add the lamb and stir fry. After the mutton changes color, change the low heat to sauté the moisture of the mutton slowly. Add salt and stir well. Add chili powder, cumin powder, and pepper powder and stir fry evenly.$Put the fried lamb on a plate covered with coriander.");

insert into ingredients (id, amount, name, action, recipe_id)
values
(default, "500g", " lamb", "cut into cubes around 2 inches", 2),
(default, "50g", "coriander", "", 2),
(default, "15g", "cumin powder", "", 2),
(default, "10g", "chill powder", "", 2),
(default, "5g","pepper powder", "", 2),
(default, " 1 tablespoon", "light soy sauce", "", 2),
(default, "3g", "salt", "", 2),
(default, "10g", "starch", "", 2),
(default, "20g", "white sesame", "", 2);



insert into recipes
values
(default, "potato beef", 10, 4, 1, "./recipe_pic/potatobeef.jpg",
"Slice beef with salt, soy sauce, a few drops of oil, and cornstarch. Cut fresh potatoes into thick slices, and cut green and red pepper into circles. Slide the beef slices quickly over and remove them for use. Add the remaining oil to the potato slices, sauté salt and sauté for a good taste, spray a little water and simmer for a while. When the potatoes are cooked, pour the beef, circle the chili, spray with soy sauce and stir-fry evenly, and sprinkle with chicken essence.");

insert into ingredients (id, amount, name, action, recipe_id)
values
(default, "500g", " beef", "cut into cubes around 2 inches", 3),
(default, "200g", "fresh potatoes", "", 3),
(default, "50g","green pepper", "", 3),
(default, "50g","red pepper", "", 3),
(default, " 1 tablespoon", "light soy sauce", "", 3),
(default, "3g", "salt", "", 3),
(default, "10g", "starch", "", 3);

insert into recipes 
values
(default, "egg fried rice", 10, 4, 1, "./recipe_pic/eggfriedrice.jpg",
" Dice carrots, onions, and cucumbers, and separate the yolk from the whole egg. Pour the egg yolk liquid into the rice and mix well, while dispersing the rice grains to make the rice grains golden brown. Heat the oil in the pan on a high fire, pour in the rice wrapped in egg mixture, and fry until the rice grains are loose. Add diced onion and diced carrot, stir fry for 2 minutes, finally add cucumber and salt, stir fry slightly.");

insert into ingredients (id, amount, name, action, recipe_id)
values
(default, "250g", " white rice", "cold rice is much better.", 4),
(default, "2", " egg yolks", "", 4),
(default, "50g"," carrot", "", 4),
(default, "20g","onion", "", 4),
(default, "50g","cucumber", "", 4),
(default, " 1 tablespoon", "light soy sauce", "", 4),
(default, "3g", "salt", "", 4);

insert into recipes
values
(default, "Crucian fish soup", 10, 4, 1, "./recipe_pic/crucianfishsoup.jpg",
" Remove the gills and clean up the crucian carp, and make 3 cuts on both sides of the fish. Fry in a frying pan. Put the fish in a casserole or soup pot, and add an appropriate amount of water to at least cover the body of the fish. Add cooking wine, salt, shallots, and ginger slices. Cover and cook on high heat. Add the wolfberry berries to the boiling pot and simmer for 20 minutes. After 20 minutes, add enoki mushrooms. After 5 minutes, sprinkle with chopped green onions.");

insert into ingredients (id, amount, name, action, recipe_id)
values
(default, "1", " crucian carp ", "", 5),
(default, "250g"," enoki mushroom ", "", 5),
(default, "10"," goji berries ", "", 5),
(default, "10g"," green onions ", "", 5),
(default, " 20g", " shallots ", "", 5),
(default, "10g", "ginger", "", 5);

insert into recipes
values
(default, " Steamed sea bass", 10, 4, 1, "./recipe_pic/steamedseabass.jpg",
"Cut half of the green onions into chopped green onions and set aside. Rinse the fish, drain the water, cut a few knives on the fish (to taste), sprinkle with a little salt, and spread well. Put the fish in the plate, spread the green onion and ginger on the fish, drizzle with 20g rice wine to remove the fishy taste. When the water in the steamer boils, add the sea bass and steam for 8 minutes, add the steamed fish soy sauce, and steam for another 2 minutes. Sprinkle with chopped green onion.");

insert into ingredients (id, amount, name, action, recipe_id)
values
(default, "1", " sea bass", "", 6),
(default, "20g"," scallion", "", 6),
(default, "30g"," steamed fish soy sauce ", "", 6),
(default, " 20g", " garlic ", "", 6),
(default, " 3g", "salt", "", 6),
(default, " 20g", "Chinese wine", "", 6),
(default, "20g", "ginger", "", 6);

insert into recipes
values
(default, "Fried shrimps", 10, 4, 1, "./recipe_pic/friedshrimps.jpg",
" Remove the shrimp thread. Cut a slit along the back of the shrimp and remove the shrimp string. Wash the shrimp, and drain the water. Shred ginger and set aside. Heat the pan with cold oil, stir-fry with shredded ginger until the oil is 80% hot, add the prawns and stir-fry after the aroma comes out. When the shrimps turn red, add salt and sugar and continue to stir-fry. Cover the pot and simmer, then pour in the right amount of tomatoes Stir the sauce evenly and it can be out of the pan.");

insert into ingredients (id, amount, name, action, recipe_id)
values
(default, "1kg", "shrimps", "", 7),
(default, " 6g", "sugar", "", 7),
(default, " 3g", "salt", "", 7),
(default, " 20g", "tomato sauce", "", 7),
(default, "20g", "ginger", "", 7);

