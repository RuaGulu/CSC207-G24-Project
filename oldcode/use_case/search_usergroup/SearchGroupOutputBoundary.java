package use_case.search_usergroup;


public interface SearchGroupOutputBoundary {
    void prepareSuccessView(SearchGroupOutputData result);

    void prepareFailView(String error);
}
