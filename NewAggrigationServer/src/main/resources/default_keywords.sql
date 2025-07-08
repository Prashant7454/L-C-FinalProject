-- Insert default keywords for common news categories
-- This script should be run after categories are created

-- Technology keywords
INSERT INTO keyword (name) VALUES 
('technology'), ('tech'), ('software'), ('hardware'), ('computer'), ('mobile'), ('smartphone'), 
('artificial intelligence'), ('ai'), ('machine learning'), ('ml'), ('blockchain'), ('cryptocurrency'),
('startup'), ('innovation'), ('digital'), ('internet'), ('web'), ('app'), ('application');

-- Business keywords
INSERT INTO keyword (name) VALUES 
('business'), ('economy'), ('finance'), ('market'), ('stock'), ('investment'), ('trading'),
('company'), ('corporate'), ('enterprise'), ('revenue'), ('profit'), ('earnings'), ('quarterly'),
('merger'), ('acquisition'), ('ipo'), ('venture capital'), ('entrepreneur'), ('startup');

-- Politics keywords
INSERT INTO keyword (name) VALUES 
('politics'), ('government'), ('election'), ('president'), ('congress'), ('senate'), ('democrat'),
('republican'), ('policy'), ('legislation'), ('bill'), ('law'), ('vote'), ('campaign'),
('political'), ('administration'), ('federal'), ('state'), ('local'), ('municipal');

-- Sports keywords
INSERT INTO keyword (name) VALUES 
('sports'), ('football'), ('basketball'), ('baseball'), ('soccer'), ('tennis'), ('golf'),
('olympics'), ('championship'), ('tournament'), ('league'), ('team'), ('player'), ('coach'),
('game'), ('match'), ('score'), ('win'), ('loss'), ('victory');

-- Entertainment keywords
INSERT INTO keyword (name) VALUES 
('entertainment'), ('movie'), ('film'), ('television'), ('tv'), ('show'), ('series'),
('actor'), ('actress'), ('director'), ('producer'), ('celebrity'), ('hollywood'), ('award'),
('music'), ('song'), ('album'), ('artist'), ('concert'), ('performance');

-- Health keywords
INSERT INTO keyword (name) VALUES 
('health'), ('medical'), ('medicine'), ('doctor'), ('hospital'), ('patient'), ('treatment'),
('disease'), ('virus'), ('infection'), ('vaccine'), ('covid'), ('pandemic'), ('epidemic'),
('wellness'), ('fitness'), ('nutrition'), ('diet'), ('exercise'), ('mental health');

-- Science keywords
INSERT INTO keyword (name) VALUES 
('science'), ('research'), ('study'), ('scientist'), ('laboratory'), ('experiment'), ('discovery'),
('space'), ('nasa'), ('planet'), ('universe'), ('galaxy'), ('star'), ('moon'), ('earth'),
('climate'), ('environment'), ('global warming'), ('pollution'), ('conservation');

-- Education keywords
INSERT INTO keyword (name) VALUES 
('education'), ('school'), ('university'), ('college'), ('student'), ('teacher'), ('professor'),
('academic'), ('research'), ('study'), ('learning'), ('teaching'), ('curriculum'), ('degree'),
('graduation'), ('scholarship'), ('tuition'), ('campus'), ('classroom'), ('lecture');

-- Note: After inserting keywords, you'll need to manually link them to categories
-- using the notification configuration feature or by directly inserting into category_keyword table 