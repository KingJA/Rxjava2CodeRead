package kingja.rxjava2coderead.read;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:TODO
 * Create Time:2019/4/24 0024 上午 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReadTest {

    public static void main(String[] args) {
        simpleSubscribe();

    }

    private static void simleThread() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("subscribe" + " 所在线程：" + Thread.currentThread().getName());
                emitter.onNext("发射1");
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe" + " 所在线程：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext" + s + " 所在线程：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError" + e.toString() + " 所在线程：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete" + " 所在线程：" + Thread.currentThread().getName());
                    }
                });
    }

    private static void simpleSubscribe() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("subscribe");
                emitter.onNext("发射1事件");
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext" + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError" + e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        observable.subscribe(observer);
    }
}
