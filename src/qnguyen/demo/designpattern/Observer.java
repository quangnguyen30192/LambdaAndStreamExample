package qnguyen.demo.designpattern;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    public static void main(String[] args) {
        Newspaper newspaper = new Newspaper();
        newspaper.register(news -> System.out.println("Client 1 got a news: " + news));
        newspaper.register(news -> System.out.println("Client 2 got a news: " + news));
        newspaper.register(news -> System.out.println("Client 3 got a news: " + news));

        newspaper.publishNews("Today is Sunday");
    }
}

interface Client {
    void update(String news);
}

interface Subject {

    void register(Client client);

    void remove(Client client);

    void publishNews(String news);
}

class Newspaper implements Subject {

    private List<Client> clients = new ArrayList<>();

    @Override
    public void register(Client client) {
        clients.add(client);
    }

    @Override
    public void remove(Client client) {
        clients.remove(client);
    }

    @Override
    public void publishNews(String news) {
        System.out.println("Published news: " + news);
        clients.forEach(client -> client.update(news));
    }
}