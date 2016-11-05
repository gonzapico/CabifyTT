package xyz.gonzapico.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;
import xyz.gonzapico.data.ApplicationTestCase;
import xyz.gonzapico.data.entity.EstimateVehicle;
import xyz.gonzapico.data.entity.VehicleType;
import xyz.gonzapico.domain.model.DomainModelEstimateVehicle;
import xyz.gonzapico.domain.model.DomainVehicleType;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by gfernandez on 5/11/16.
 */

public class EstimateDataMapperTest extends ApplicationTestCase {

  private static final String FAKE_NAME = "fake name";
  private static final String FAKE_CURRENCY = "EUR";
  private static final String FAKE_CURRENCY_SYMBOL = "€";
  private static final String FAKE_PRICE_FORMATTED = "3.00 €";
  private static final long FAKE_TOTAL_PRICE = 300;
  private static final String FAKE_CURRENCY_NULL = null;
  private static final String FAKE_CURRENCY_SYMBOL_NULL = null;
  private static final String FAKE_PRICE_FORMATTED_NULL = "N/A";
  private static final long FAKE_TOTAL_PRICE_NULL = -1;
  private static final Boolean FAKE_ASAP_ONLY = false;
  private static final String FAKE_DESCRIPTION = "description fake";
  private static final String FAKE_ICON =
      "https://test.cabify.com/images/icons/vehicle_type/access_54.png";
  private static final String FAKE_SHORT_NAME = "short name";
  private EstimateDataMapper estimateDataMapper;

  @Before public void setUp() throws Exception {
    estimateDataMapper = new EstimateDataMapper();
  }

  @Test public void transformToDataBodyRequest() {

  }

  @Test public void transformToDomainVehicleList() {
    Response<List<EstimateVehicle>> fakeEstimateVehicleAPIResponse =
        createFakeEntityListEstimateVehicle();

    List<DomainModelEstimateVehicle> listOfTransformedDomainEstimateVehicle =
        estimateDataMapper.transformToDomainVehicleList(fakeEstimateVehicleAPIResponse);

    for (DomainModelEstimateVehicle estimateVehicleDomain : listOfTransformedDomainEstimateVehicle) {
      assertThat(estimateVehicleDomain.getCurrency(), is(FAKE_CURRENCY));
      assertThat(estimateVehicleDomain.getCurrencySymbol(), is(FAKE_CURRENCY_SYMBOL));
      assertThat(estimateVehicleDomain.getPriceFormatted(), is(FAKE_PRICE_FORMATTED));
      assertThat(estimateVehicleDomain.getTotalPrice(), is(FAKE_TOTAL_PRICE));
      DomainVehicleType domainVehicleType = estimateVehicleDomain.getVehicleType();
      assertThat(domainVehicleType.getCurrency(), is(FAKE_CURRENCY));
      assertThat(domainVehicleType.getDescription(), is(FAKE_DESCRIPTION));
      // We don't want a null image. This may compromise the presentation layer.
      assertNotNull(domainVehicleType.getIcon());
      assertThat(domainVehicleType.getIcon(), is(FAKE_ICON));
      assertThat(domainVehicleType.getName(), is(FAKE_NAME));
      assertThat(domainVehicleType.getShortName(), is(FAKE_SHORT_NAME));
    }
  }

  private Response<List<EstimateVehicle>> createFakeEntityListEstimateVehicle() {
    List<EstimateVehicle> fakeListEstimateVehicle = new ArrayList<>();

    int n = 7;
    for (int i = 0; i < n; i++) {
      EstimateVehicle fakeEstimateVehicle = new EstimateVehicle();

      fakeEstimateVehicle.setCurrency(FAKE_CURRENCY);
      fakeEstimateVehicle.setCurrencySymbol(FAKE_CURRENCY_SYMBOL);
      fakeEstimateVehicle.setPriceFormatted(FAKE_PRICE_FORMATTED);
      fakeEstimateVehicle.setTotalPrice(FAKE_TOTAL_PRICE);
      fakeEstimateVehicle.setVehicleType(createFakeVehicleType());

      fakeListEstimateVehicle.add(fakeEstimateVehicle);
    }

    return Response.success(fakeListEstimateVehicle);
  }

  private VehicleType createFakeVehicleType() {
    VehicleType fakeVehicleType = new VehicleType();

    fakeVehicleType.setCurrency(FAKE_CURRENCY);
    fakeVehicleType.setName(FAKE_NAME);
    fakeVehicleType.setAsapOnly(FAKE_ASAP_ONLY);
    fakeVehicleType.setDescription(FAKE_DESCRIPTION);
    fakeVehicleType.setIcon(FAKE_ICON);
    fakeVehicleType.setShortName(FAKE_SHORT_NAME);

    return fakeVehicleType;
  }

  private Response<List<EstimateVehicle>> createFakeEntityListEstimateVehicleNull() {
    List<EstimateVehicle> fakeListEstimateVehicle = new ArrayList<>();

    int n = 7;
    for (int i = 0; i < n; i++) {
      EstimateVehicle fakeEstimateVehicle = new EstimateVehicle();

      fakeEstimateVehicle.setCurrency(FAKE_CURRENCY_NULL);
      fakeEstimateVehicle.setCurrencySymbol(FAKE_CURRENCY_SYMBOL_NULL);
      fakeEstimateVehicle.setPriceFormatted(FAKE_PRICE_FORMATTED_NULL);
      fakeEstimateVehicle.setTotalPrice(FAKE_TOTAL_PRICE_NULL);

      fakeListEstimateVehicle.add(fakeEstimateVehicle);
    }

    return Response.success(fakeListEstimateVehicle);
  }
}
