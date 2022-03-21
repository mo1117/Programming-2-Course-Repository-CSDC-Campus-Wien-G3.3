package at.ac.fhcampuswien;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some Tests for the AppController Class
 * Author @Moritz Lindner
 */

public class AppTest {

    private AppController ctrl = new AppController();

    @BeforeAll
    public static void init() {
        System.out.println("Testing...");
    }

    @AfterAll
    public static void finish() {
        System.out.println("Finished!");
    }

    @Test
    @DisplayName("setArticles - Test 1")
    public void setArticles1() {
        try {
            Method m = AppController.class.getMethod("setArticles", List.class);
            assertTrue(m.getReturnType() == void.class, "This method should not have a return type!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'setArticles' might be missing. Also check parameters.");
        }
    }

    @Test
    @DisplayName("setArticles - Test 2")
    public void setArticles2() {
        try {
            Method setter = AppController.class.getMethod("setArticles", List.class);
            Field amount = AppController.class.getField("articleCount");
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
            }};

            setter.invoke(ctrl, articles);
            assertEquals(articles.size(), amount.get(ctrl), "'setArticles' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'setArticles' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("setArticles - Test 3")
    public void setArticles3() {
        try {
            Method setter = AppController.class.getMethod("setArticles", List.class);
            Field amount = AppController.class.getField("articleCount");
            List<Article> toBeDeleted = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Article3"));
                add(new Article("Author4", "Article4"));

            }};

            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
            }};

            setter.invoke(ctrl, toBeDeleted);
            setter.invoke(ctrl, articles);
            assertEquals(articles.size(), amount.get(ctrl), "'setArticles' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'setArticles' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("setArticles - Test 4")
    public void setArticles4() {
        try {
            Method setter = AppController.class.getMethod("setArticles", List.class);
            Field amount = AppController.class.getField("articleCount");
            List<Article> articles = new LinkedList();

            setter.invoke(ctrl, articles);
            assertEquals(articles.size(), amount.get(ctrl), "'setArticles' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'setArticles' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("getArticleCount - Test 1")
    public void getArticleCount1() {
        try {
            Method m = AppController.class.getMethod("getArticleCount");
            assertTrue(m.getReturnType() == int.class, "This method should return an integer!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'getArticleCount' might be missing. Also check parameters.");
        }
    }

    @Test
    @DisplayName("getArticleCount - Test 2")
    public void getArticleCount2() {
        try {
            Method getter = AppController.class.getMethod("getArticleCount");
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Book1"));
            }};

            Field amount = AppController.class.getField("articleCount");
            amount.set(ctrl, articles.size());
            assertEquals(articles.size(), getter.invoke(ctrl), "'getArticleCount' is not working as it should! ");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'getArticleCount' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("getArticleCount - Test 3")
    public void getArticleCount3() {
        try {
            Method getter = AppController.class.getMethod("getArticleCount");
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Article3"));
                add(new Article("Author4", "Article4"));
                add(new Article("Author5", "Article5"));
            }};

            Field amount = AppController.class.getField("articleCount");
            amount.set(ctrl, articles.size());
            assertEquals(articles.size(), getter.invoke(ctrl), "'getArticleCount' is not working as it should! ");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'getArticleCount' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("getArticleCount - Test 4")
    public void getArticleCount4() {
        try {
            Method getter = AppController.class.getMethod("getArticleCount");
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Article3"));
            }};
            articles.add(new Article("Author4", "Article4"));
            articles.add(new Article("Author5", "Article5"));
            articles.add(new Article("Author6", "Article6"));
            articles.add(new Article("Author7", "Article7"));

            Field amount = AppController.class.getField("articleCount");
            amount.set(ctrl, articles.size());
            assertEquals(articles.size(), getter.invoke(ctrl), "'getArticleCount' is not working as it should! ");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'getArticleCount' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("getArticleCount - Test 5")
    public void getArticleCount5() {
        try {
            Method getter = AppController.class.getMethod("getArticleCount");
            List<Article> articles = new LinkedList();
            Field amount = AppController.class.getField("articleCount");
            amount.set(ctrl, articles.size());

            assertEquals(articles.size(), getter.invoke(ctrl), "'getArticleCount' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'getArticleCount' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("getTopHeadlinesAustria - Test 1")
    public void getTopHeadlinesAustria1() {
        try {
            Method m = AppController.class.getMethod("getTopHeadlinesAustria");
            assertTrue(m.getReturnType() == List.class, "This method should return a List!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("'getTopHeadlinesAustria' might be missing. Also check parameters.");
        }
    }

    @Test
    @DisplayName("getTopHeadlinesAustria - Test 2")
    public void getTopHeadlinesAustria2() {
        try {
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Article3"));
                add(new Article("Author4", "Article4"));
                add(new Article("Author5", "Article5"));
            }};
            Field amount = AppController.class.getField("articleCount");
            amount.set(ctrl, articles.size());
            Method m = AppController.class.getMethod("getTopHeadlinesAustria");
            assertEquals(articles.size(), m.invoke(ctrl), "'getTopHeadlinesAustria' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("'getTopHeadlinesAustria' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("getAllNewsBitcoin - Test 1")
    public void getAllNewsBitcoin1() {
        try {
            Method m = AppController.class.getMethod("getAllNewsBitcoin");
            assertTrue(m.getReturnType() == List.class, "This method should return a List!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("'getAllNewsBitcoin' might be missing. Also check parameters.");
        }
    }

    @Test
    @DisplayName("getAllNewsBitcoin - Test 2")
    public void getAllNewsBitcoin2() {
        try {
            Method m = AppController.class.getMethod("getAllNewsBitcoin");
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Bitcoin1"));
                add(new Article("Author4", "Bitcoin2"));
                add(new Article("Author5", "MaybeBitcoin3"));
                add(new Article("Author6", "BitterCoins4"));
                add(new Article("Author7", "Euros5"));
            }};

            List<Article> articles2 = (List<Article>) m.invoke(null);
            assertEquals(3, articles2.size(), "'getAllNewsBitcoin' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'getAllNewsBitcoin' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("getAllNewsBitcoin - Test 3")
    public void getAllNewsBitcoin3() {
        try {
            Method m = AppController.class.getMethod("getAllNewsBitcoin");
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Article3"));
                add(new Article("Author4", "Article4"));
                add(new Article("Author5", "Article5"));
                add(new Article("Author6", "Article6"));
                add(new Article("Author7", "Bit coin7"));
            }};

            List<Article> articles2 = (List<Article>) m.invoke(null);
            assertEquals(0, articles2.size(), "'getAllNewsBitcoin' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'getAllNewsBitcoin' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("getAllNewsBitcoin - Test 4")
    public void getAllNewsBitcoin4() {
        try {
            Method m = AppController.class.getMethod("getAllNewsBitcoin");
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "BiTCoin1"));
                add(new Article("Author4", "BiTCoinsss2"));
                add(new Article("Author5", "MaybeBiTCoin3"));
                add(new Article("Author6", "BitterCoins4"));
                add(new Article("Author7", "Euros5"));
            }};

            List<Article> articles2 = (List<Article>) m.invoke(null);
            assertEquals(3, articles2.size(), "'getAllNewsBitcoin' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Method 'getAllNewsBitcoin' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("filterList - Test 1")
    public void filterList1() {
        try {
            Method m = AppController.class.getMethod("filterList", String.class, List.class);
            m.setAccessible(true);
            assertTrue(m.getReturnType() == List.class, "This method should return a List!");
            assertTrue(m.getModifiers() == Modifier.PROTECTED, "This method should be protected!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("'filterList' might be missing. Also check parameters.");
        }
    }

    @Test
    @DisplayName("filterList - Test 2")
    public void filterList2() {
        try {
            Method m = AppController.class.getMethod("filterList", String.class, List.class);
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Magazine1"));
                add(new Article("Author4", "Magazine2"));
                add(new Article("Author5", "Magazine3"));
                add(new Article("Author6", "Magazine4"));
                add(new Article("Author7", "Magazine5"));
            }};

            List<Article> articles2 = (List<Article>) m.invoke(null, "Magazine", articles);
            assertEquals(5, articles2.size(), "'filterList' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("'filterList' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("filterList - Test 3")
    public void filterList3() {
        try {
            Method m = AppController.class.getMethod("filterList", String.class, List.class);
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Magazine1"));
                add(new Article("Author4", "Magazine2"));
                add(new Article("Author5", "Magazine3"));
                add(new Article("Author6", "Magazine4"));
                add(new Article("Author7", "Magazine5"));
            }};

            List<Article> articles2 = (List<Article>) m.invoke(null, "SpiderMan", articles);
            assertEquals(0, articles2.size(), "'filterList' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("'filterList' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("filterList - Test 4")
    public void filterList4() {
        try {
            Method m = AppController.class.getMethod("filterList", String.class, List.class);
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Magazine1"));
                add(new Article("Author4", "Magazine2"));
                add(new Article("Author5", "Magazine3"));
                add(new Article("Author6", "Magazine4"));
                add(new Article("Author7", "Magazine5"));
            }};

            List<Article> articles2 = (List<Article>) m.invoke(null, "2", articles);
            assertEquals(2, articles2.size(), "'filterList' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("'filterList' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }

    @Test
    @DisplayName("filterList - Test 5")
    public void filterList5() {
        try {
            Method m = AppController.class.getMethod("filterList", String.class, List.class);
            List<Article> articles = new LinkedList() {{
                add(new Article("Author1", "Article1"));
                add(new Article("Author2", "Article2"));
                add(new Article("Author3", "Magazine1"));
                add(new Article("Author4", "Magazine2"));
                add(new Article("Author5", "Magazine3"));
                add(new Article("Author6", "Magazine4"));
                add(new Article("Author7", "Magazine5"));
            }};

            List<Article> articles2 = (List<Article>) m.invoke(null, "GAzINe", articles);
            assertEquals(5, articles2.size(), "'filterList' is not working as it should!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("'filterList' might be missing." + System.lineSeparator() + "There might also be some other " +
                    "problems.");
        }
    }
}
