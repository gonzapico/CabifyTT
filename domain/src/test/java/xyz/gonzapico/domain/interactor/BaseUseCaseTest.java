package xyz.gonzapico.domain.interactor;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;
import xyz.gonzapico.domain.executor.PostExecutionThread;
import xyz.gonzapico.domain.executor.ThreadExecutor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by gfernandez on 7/11/16.
 */

public class BaseUseCaseTest {

  private BaseUseCaseTestClass useCase;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @org.junit.Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.useCase = new BaseUseCaseTestClass(mockThreadExecutor, mockPostExecutionThread);
  }

  @Test @SuppressWarnings("unchecked") public void testBuildUseCaseObservableReturnCorrectResult() {
    TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
    TestScheduler testScheduler = new TestScheduler();
    given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

    useCase.execute(testSubscriber);

    assertThat(testSubscriber.getOnNextEvents().size(), is(0));
  }

  @Test public void testSubscriptionWhenExecutingUseCase() {
    TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

    useCase.execute(testSubscriber);
    useCase.unsubscribe();

    assertThat(testSubscriber.isUnsubscribed(), is(true));
  }

  private static class BaseUseCaseTestClass extends BaseUseCase {

    protected BaseUseCaseTestClass(ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
      super(threadExecutor, postExecutionThread);
    }

    @Override protected Observable buildUseCaseObservable() {
      return Observable.empty();
    }

    @Override public void execute(Subscriber UseCaseSubscriber) {
      super.execute(UseCaseSubscriber);
    }
  }
}
