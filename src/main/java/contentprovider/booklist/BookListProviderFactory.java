package contentprovider.booklist;

/**
 * Created by jamesji on 15/03/2017.
 */
public class BookListProviderFactory {
    private static volatile IBookListProvider arrivalProvider, recommendationProvider;

    private BookListProviderFactory() {

    }

    public static IBookListProvider getArrivalProvider() {
        if (arrivalProvider == null) {
            synchronized (BookListProviderFactory.class) {
                if (arrivalProvider == null) {
                    arrivalProvider = new SQLArrivalBookListProvider();
                }
            }
        }
        return arrivalProvider;
    }

    public static IBookListProvider getRecommendationProvider() {
        if (recommendationProvider == null) {
            synchronized (BookListProviderFactory.class) {
                if (recommendationProvider == null) {
                    recommendationProvider = new SQLRecommendationBookListProvider();
                }
            }
        }
        return recommendationProvider;
    }
}
