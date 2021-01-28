package diiage.potherat.demo.demoapp3.ui.home;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.model.Quote;

public class HomeViewModel extends ViewModel {

    private QuoteRepository quoteRepository;

    @Inject
    @ViewModelInject
    public HomeViewModel(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public LiveData<Integer> getNumberOfQuotes() {
        return this.quoteRepository.getCountQuote();
    }

    public LiveData<Integer> getDistinctSources() {
        return this.quoteRepository.getCountSource();
    }

    public LiveData<Quote> getLastQuote() {
        return this.quoteRepository.getLastQuote();
    }
}