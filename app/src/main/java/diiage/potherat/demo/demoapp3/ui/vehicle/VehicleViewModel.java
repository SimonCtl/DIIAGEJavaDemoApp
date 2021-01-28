package diiage.potherat.demo.demoapp3.ui.vehicle;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.common.Event;
import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.dal.repository.SWRepository;
import diiage.potherat.demo.demoapp3.dal.retrofit.SwRetrofit;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiErrorResponse;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiSuccessResponse;
import diiage.potherat.demo.demoapp3.model.Quote;
import diiage.potherat.demo.demoapp3.model.sw.SWModelList;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

public class VehicleViewModel extends ViewModel {

    SwRetrofit swRepository;
    ExecutorService executorService;
    private final MutableLiveData<String> id = new MutableLiveData<>();
    private LiveData<Vehicle> vehicle = new MutableLiveData<>();



    @Inject
    @ViewModelInject
    public VehicleViewModel(ExecutorService executorService, SwRetrofit swRepository){
        this.swRepository = swRepository;
        this.executorService = executorService;
    }

    public MutableLiveData<String> getId() {
        return id;
    }

    public LiveData<Vehicle> getVehicle() {
        return vehicle;
    }

    public void searchVehicle(){
        String value = this.id.getValue() != null ? this.id.getValue() : "";
        vehicle = Transformations.map(swRepository.getVehicle(value), input -> {
            if(input instanceof ApiSuccessResponse){
                return ((ApiSuccessResponse<Vehicle>)input).getBody();
            }
            else if(input instanceof ApiErrorResponse){
                Log.e("debug",((ApiErrorResponse<Vehicle>)input).getErrorMessage());
            }
            return null;
        });
    }

}