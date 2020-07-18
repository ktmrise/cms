import com.ktm.mapper.ArticleMapper;
import com.ktm.model.Article;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class Test {


    @Resource
    private ArticleMapper articleMapper;


    @org.junit.jupiter.api.Test
    void test() {
        Article article = new Article();
        article.setAuthor("ktm");
        article.setContent("11");
        System.out.println(articleMapper.insert(article));
    }
}
