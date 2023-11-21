package dataProviders;

import dataObjects.speaker;
import org.testng.annotations.DataProvider;

public class speakersData {
    @DataProvider(name = "speakersProvider")
    public Object[][] provideData() {
        return new Object[][]{
                {new speaker(1,"Burt Bear","Software Engineer","burt@example.com","+1-541-754-3010",
                        "Burt is a Bear. Burt's interests include poetry, dashing space heroes, and lions. Say hello on social media!")},

                {new speaker(2,"Charlie Cheetah","Software Engineer","charlie@example.com","+1-541-754-3010",
                        "Charlie is a Cheetah. Charlie's interests include country music, plush animals, pyrotechnics, and skeletons. Say hello on social media!")},

                {new speaker(3,"Donald Duck","Software Engineer","donald@example.com","+1-541-754-3010",
                        "Donald is a Duck. Donald's interests include carpentry, superheroes, merpeople, and glam rock. Say hello on social media!")},

                {new speaker(4,"Eva Eagle","Developer Advocate","eva@example.com","+1-541-754-3010",
                        "Eva is an Eagle. Eva's interests include ants, seashells, and cupcakes. Say hello on social media!")},

                {new speaker(5,"Ellie Elephant","Software Engineer","ellie@example.com","+1-541-754-3010",
                        "Ellie is an Elephant. Ellie's interests include pocket watches, pool, hand fans, and ninjas. Say hello on social media!")},

                {new speaker(6,"Gino Giraffe","Software Engineer","gino@example.com§","+1-541-754-3010",
                        "Gino is a Giraffe. Gino's interests include candy-making, unicorns, and birdhouses. Say hello on social media!")},

                {new speaker(7,"Isabella Iguana","Software Engineer","isabella@example.com","+1-541-754-3010",
                        "Isabella is an Iguana. Isabella's interests include crystals, architecture, and candle-making. Say hello on social media!")},

                {new speaker(8,"Karl Kitten","Developer Advocate","karl@example.com","+1-541-754-3010",
                        "Karl is a Kitten. Karl's interests include skiing, jewelry, and needlepoint. Say hello on social media!")},

                {new speaker(9,"Lionel Lion","Developer Advocate","lionel@example.com","+1-541-754-3010",
                        "Lionel is a Lion. Lionel's interests include lizards and mathematics. Say hello on social media!")},

                {new speaker(10,"Molly Mouse","Software Engineer","molly@example.com","+1-541-754-3010",
                        "Molly is a Mouse. Molly's interests include werewolves and magic. Say hello on social media!")},

                {new speaker(11,"Paul Puppy","Software Engineer","paul@example.com","+1-541-754-3010",
                        "Paul is a Puppy. Paul's interests include maps, whales, and dragons. Say hello on social media!")},

                {new speaker(12,"Rachel Rabbit","Senior Software Engineer","rachel@example.com","+1-541-754-3010",
                        "Rachel is a Rabbit. Rachel's interests include clowns, skeletons, and yo-yos. Say hello on social media!")},

                {new speaker(13,"Ted Turtle","Software Engineer","ted@example.com","+1-541-754-3010",
                        "Ted is a Turtle. Ted's interests include butterflies, skiing, and cupcakes. Say hello on social media!")}

        };
    }
}
