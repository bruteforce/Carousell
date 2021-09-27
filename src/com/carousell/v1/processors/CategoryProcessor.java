package com.carousell.v1.processors;

    import java.util.List;

    import com.carousell.v1.model.Listing;

public interface CategoryProcessor {

  List<Listing> getListingsFromCategory(String categoryName) throws Exception;

  String getTopCategoryForUser();
}
