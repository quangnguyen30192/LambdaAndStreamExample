package qnguyen.demo.lambda;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Quang Nguyen on 9/21/2016.
 */

class Article {

    private final String title;
    private final String author;
    private final List<String> tags;

    public Article(String title, String author, List<String> tags) {
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTags() {
        return tags;
    }
}

// java 8: Optional is eager, Stream is Lazy
// java 8: Optional is eager and lazy (because it has Stream as an attribute) as the way we want , Stream is Lazy
class Inpractices {

    public static void main(String[] args) {
        Inpractices inpractices = new Inpractices();
        Optional<Article> first = inpractices.findFirst("1");


    }

    public Optional<Article> findFirst(String id) {
        return Optional.of(new Article(null, null, null));
    }

    // java 8: optional is not quite good because it's eager
    public Stream<Article> find(Collection<String> ids) {
        Inpractices test = new Inpractices();
        return ids.stream().map(test::findFirst).filter(Optional::isPresent).map(Optional::get);
        // or return ids.stream().map(test::findFirst).flatMap(article -> article.isPresent() ? Stream.of(article.get()) : Stream.empty());

        // java 9 will support: now it's look like better than previous one
//        return articleIds.stream()
//                          .map(this::findFirst)
//                          .flatMap(Optional::stream)
//        OR return articleIds.stream()
//                          .flatMap(id -> findFirst(id).stream());
    }

    //We can now operate lazily on Optional.
//    public List<Order> findOrdersForCustomer(String customerId) {
//        return findCustomer(customerId)
//                // 'List<Order> getOrders(Customer)' is expensive;
//                // this is 'Optional::map', which is eager
//                .map(this::getOrders)
//                .orElse(new ArrayList<>());
//    }
//
//    public Stream<Order> findOrdersForCustomer(String customerId) {
//        return findCustomer(customerId)
//                .stream()
//                // this is 'Stream::map', which is lazy
//                .map(this::getOrders)
//                .flatMap(List::stream);
//    }

    public Optional<Article> getFirstJavaArticle(List<Article> articles) {
//        for (Article article : articles) {
//            if (article.getTags().contains("Java")) {
//                return article;
//            }
//        }
//
//        return null;
        return articles.stream()
                       .filter(article -> article.getTags().contains("Java"))
                       .findFirst();
    }

    public List<Article> getAllJavaArticles(List<Article> articles) {

//        List<Article> result = new ArrayList<>();
//
//        for (Article article : articles) {
//            if (article.getTags().contains("Java")) {
//                result.add(article);
//            }
//        }
//
//        return result;
        return articles.stream()
                       .filter(article -> article.getTags().contains("Java"))
                       .collect(Collectors.toList());
    }

    public Map<String, List<Article>> groupByAuthor(List<Article> articles) {
//        Map<String, List<Article>> result = new HashMap<>();
//
//        for (Article article : articles) {
//            if (result.containsKey(article.getAuthor())) {
//                result.get(article.getAuthor()).add(article);
//            } else {
//                ArrayList<Article> articles = new ArrayList<>();
//                articles.add(article);
//                result.put(article.getAuthor(), articles);
//            }
//        }
//
//        return result;
        return articles.stream().collect(Collectors.groupingBy(Article::getAuthor));
    }

    public Set<String> getDistinctTags(List<Article> articles) {
//        Set<String> result = new HashSet<>();
//
//        for (Article article : articles) {
//            result.addAll(article.getTags());
//        }
//
//        return result;
        return articles.stream().flatMap(article -> article.getTags().stream()).collect(Collectors.toSet());
    }
}
