package xyz.gonzapico.domain.interactor;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import xyz.gonzapico.domain.executor.PostExecutionThread;
import xyz.gonzapico.domain.executor.ThreadExecutor;
import xyz.gonzapico.domain.model.DomainModelBodyRequestStops;
import xyz.gonzapico.domain.model.DomainModelLocation;
import xyz.gonzapico.domain.model.DomainModelStop;
import xyz.gonzapico.domain.repository.EstimateDomainRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by gfernandez on 7/11/16.
 */
public class GetEstimationTest {
  private DomainModelBodyRequestStops fakeBodyRequest;
  private GetEstimation getEstimation;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private EstimateDomainRepository mockEstimateRepository;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    fakeBodyRequest = createFakeBodyRequest();

    getEstimation =
        new GetEstimation(mockEstimateRepository, mockThreadExecutor, mockPostExecutionThread);
    getEstimation.setBodyRequestStops(fakeBodyRequest);
  }

  @Test public void testGetUserListUseCaseObservableHappyCase() {
    getEstimation.buildUseCaseObservable();

    verify(mockEstimateRepository).getEstimationVehicleList(fakeBodyRequest);
    verifyNoMoreInteractions(mockEstimateRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }

  private DomainModelBodyRequestStops createFakeBodyRequest() {
    DomainModelBodyRequestStops fakeBodyRequestResult = new DomainModelBodyRequestStops();

    fakeBodyRequestResult.setStartAt("2015-12-23 15:09");
    List<DomainModelStop> listOfStops = new ArrayList<>();
    DomainModelStop stopOne = new DomainModelStop();
    List<DomainModelLocation> listOfModelLocations = new ArrayList<>();
    DomainModelLocation locationOne = new DomainModelLocation();
    locationOne.setLatitude((long) 0.1);
    locationOne.setLatitude((long) 0.1);
    DomainModelLocation locationTwo = new DomainModelLocation();
    locationOne.setLatitude((long) 40.522751);
    locationOne.setLatitude((long) -3.890156);
    listOfModelLocations.add(locationOne);
    listOfModelLocations.add(locationTwo);
    stopOne.setLocationList(listOfModelLocations);
    listOfStops.add(stopOne);
    fakeBodyRequestResult.setStops(listOfStops);

    return fakeBodyRequestResult;
  }
}