package xyz.gonzapico.data.repository;

/**
 * Created by gfernandez on 3/11/16.
 */

import android.content.Context;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Response;
import rx.Observable;
import xyz.gonzapico.data.ApplicationTestCase;
import xyz.gonzapico.data.RestServiceTestHelper;
import xyz.gonzapico.data.entity.EstimateVehicle;
import xyz.gonzapico.data.entity.StopsBodyRequest;
import xyz.gonzapico.data.entity.mapper.EstimateDataMapper;
import xyz.gonzapico.data.repository.datasource.EstimateDataStore;
import xyz.gonzapico.data.repository.datasource.EstimateDataStoreFactory;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by gfernandez on 16/08/16.
 */
public class EstimateRepositoryTest extends ApplicationTestCase {
  @Rule public ExpectedException expectedException = ExpectedException.none();
  private EstimateRepository estimateRepository;

  @Mock private EstimateDataStoreFactory mockEstimateDataStoreFactory;
  @Mock private EstimateDataMapper mockEstimateDataMapper;
  @Mock private EstimateDataStore mockEstimateDataStore;
  @Mock private EstimateVehicle mockEstimateVehicle;
  @Mock private Context mockContext;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    estimateRepository =
        new EstimateRepository(mockEstimateDataStoreFactory, mockEstimateDataMapper);

    given(mockEstimateDataStoreFactory.createCloudDataStore()).willReturn(mockEstimateDataStore);
  }

  @Test public void testGetConfigurationHappyCase() {
    String bodyRequest = "body_request.json";
    Response<List<EstimateVehicle>> entityEstimateVehicleList =
        Response.success(new ArrayList<EstimateVehicle>());
    StopsBodyRequest stopsBodyRequest = null;
    try {
      stopsBodyRequest = (StopsBodyRequest) RequestBody.create(MediaType.parse("application/json"),
          RestServiceTestHelper.getStringFromFile(mockContext, bodyRequest));
      given(mockEstimateDataStore.estimationVehicleList(stopsBodyRequest)).willReturn(Observable.just(entityEstimateVehicleList));
    }
    catch (Exception e){

    }
    estimateRepository.getEstimationVehicelList();

    verify(mockEstimateDataStoreFactory).createCloudDataStore();
    verify(mockEstimateDataStore).estimationVehicleList(stopsBodyRequest);
  }
}
