package lab8;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsParser {
    private static final String[] URLS = {
            "http://fat.urfu.ru/index.html",
            "https://fat.urfu.ru/index.html",
            "https://fiit-urfu.ru/"
    };
    private static final String FILE_PATH = "src/lab8/news.txt";
    private static final int MAX_ATTEMPTS = 3;
    private static final int MAX_NEWS_COUNT = 10;
    private static final Pattern DATE_PATTERN = Pattern.compile("\\b\\d{1,2}[./]\\d{1,2}[./]\\d{2,4}\\b");

    public static void main(String[] args) {
        try {
            List<NewsItem> news = loadNews();
            String result = formatNews(news);

            System.out.print(result);
            writeNewsToFile(result);
            System.out.println("Новости записаны в файл: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Не удалось получить HTML-код страницы: " + e.getMessage());
        }
    }

    private static List<NewsItem> loadNews() throws IOException {
        IOException lastException = null;

        for (String url : URLS) {
            try {
                Document doc = loadDocumentWithRetry(url);
                if (isDefaultServerPage(doc)) {
                    System.out.println("Страница не содержит сайта с новостями: " + url);
                    continue;
                }

                List<NewsItem> news = parseOldFatNews(doc);

                if (news.isEmpty()) {
                    news = parseGenericNews(doc);
                }

                if (!news.isEmpty()) {
                    return news;
                }
                System.out.println("На странице не найдены новости: " + url);
            } catch (IOException e) {
                lastException = e;
                System.out.println("Не удалось загрузить страницу " + url + ": " + e.getMessage());
            }
        }

        if (lastException != null) {
            throw lastException;
        }

        List<NewsItem> result = new ArrayList<>();
        result.add(new NewsItem("Новости не найдены. Возможно, изменилась структура страницы.", "Дата не указана"));
        return result;
    }

    private static boolean isDefaultServerPage(Document doc) {
        String title = cleanText(doc.title());
        String body = doc.body() == null ? "" : cleanText(doc.body().text());
        return title.contains("Apache2 Debian Default Page") || body.contains("It works!");
    }

    private static Document loadDocumentWithRetry(String url) throws IOException {
        IOException lastException = null;

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            try {
                return Jsoup.connect(url)
                        .userAgent("Mozilla/5.0")
                        .timeout(15000)
                        .ignoreHttpErrors(true)
                        .get();
            } catch (IOException e) {
                lastException = e;
                System.out.println("Ошибка подключения к " + url
                        + ", попытка " + attempt + " из " + MAX_ATTEMPTS);
            }
        }

        throw lastException;
    }

    private static List<NewsItem> parseOldFatNews(Document doc) {
        List<NewsItem> result = new ArrayList<>();
        Elements titles = doc.select(".blocktitle");

        for (Element titleElement : titles) {
            if (result.size() >= MAX_NEWS_COUNT) {
                break;
            }

            Element parent = titleElement.parent();
            String title = cleanText(titleElement.text());
            String date = "";

            if (parent != null) {
                Element dateElement = parent.selectFirst(".blockdate");
                if (dateElement != null) {
                    date = cleanText(dateElement.text());
                }
            }

            if (date.isEmpty()) {
                Element nextDate = titleElement.nextElementSibling();
                if (nextDate != null && nextDate.hasClass("blockdate")) {
                    date = cleanText(nextDate.text());
                }
            }

            if (!title.isEmpty()) {
                result.add(new NewsItem(title, date.isEmpty() ? "Дата не указана" : date));
            }
        }

        return result;
    }

    private static List<NewsItem> parseGenericNews(Document doc) {
        List<NewsItem> result = new ArrayList<>();

        Elements cards = doc.select("article, [class*=news], [class*=post], [class*=card]");
        for (Element card : cards) {
            addNewsFromElement(result, card);
            if (result.size() >= MAX_NEWS_COUNT) {
                return result;
            }
        }

        return result;
    }

    private static void addNewsFromElement(List<NewsItem> result, Element element) {
        Element titleElement = element.selectFirst("h1, h2, h3, h4, a");
        if (titleElement == null) {
            return;
        }

        String title = cleanText(titleElement.text());
        if (!isSuitableTitle(title) || containsTitle(result, title)) {
            return;
        }

        String date = "";
        Element dateElement = element.selectFirst("time, .date, [class*=date]");
        if (dateElement != null) {
            date = cleanText(dateElement.text());
        }
        if (date.isEmpty()) {
            date = extractDate(element.text());
        }

        result.add(new NewsItem(title, date.isEmpty() ? "Дата не указана" : date));
    }

    private static String findDateNear(Element element) {
        Element parent = element.parent();
        if (parent == null) {
            return "Дата не указана";
        }

        String date = extractDate(parent.text());
        return date.isEmpty() ? "Дата не указана" : date;
    }

    private static String extractDate(String text) {
        Matcher matcher = DATE_PATTERN.matcher(text);
        return matcher.find() ? matcher.group() : "";
    }

    private static boolean isSuitableTitle(String title) {
        return title.length() >= 10
                && !title.equalsIgnoreCase("Новости")
                && !title.equalsIgnoreCase("Продукты")
                && !title.equalsIgnoreCase("Контакты")
                && !title.startsWith("Читайте ")
                && !title.startsWith("Слушайте ")
                && !title.startsWith("Смотрите ");
    }

    private static boolean containsTitle(List<NewsItem> news, String title) {
        for (NewsItem item : news) {
            if (item.title.equalsIgnoreCase(title)) {
                return true;
            }
        }

        return false;
    }

    private static String formatNews(List<NewsItem> news) {
        StringBuilder result = new StringBuilder();

        for (NewsItem item : news) {
            result.append("Тема : ").append(item.title).append('\n')
                    .append("Дата : ").append(item.date).append("\n\n");
        }

        return result.toString();
    }

    private static String cleanText(String text) {
        return text == null ? "" : text.replace('\u00A0', ' ').replaceAll("\\s+", " ").trim();
    }

    private static void writeNewsToFile(String news) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(news);
        }
    }

    private static class NewsItem {
        private final String title;
        private final String date;

        private NewsItem(String title, String date) {
            this.title = title;
            this.date = date;
        }
    }
}
