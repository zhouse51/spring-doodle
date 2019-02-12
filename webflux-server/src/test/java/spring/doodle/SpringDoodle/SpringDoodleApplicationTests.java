package spring.doodle.SpringDoodle;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDoodleApplicationTests {
	@Test
	public void contextLoads() throws InterruptedException {
		Flux<Long> l = Flux.interval(Duration.ofSeconds(1))
			.map(x-> publishLong(x));
		
		l.subscribe(x->processSubscribe(x));
		
		for (int i = 0; i< 100; i ++) {
			Thread.sleep(2000);
			System.out.println("Other process " + i);
		}
		l.blockLast();
	}
	
	private Long publishLong(long x) {
		Random r = new Random();
		long i = r.nextLong();
		System.out.println("Working to publish: " + x + ": " + i);
		return i;
	}
	
	private void processSubscribe(long l) {
		Executor executor = Executors.newFixedThreadPool(3);
		
		CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
			for (int i = 0; i < 5 ;i++) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(l + ": In async 1 thread for seed: " + l);
			}
			
		}, executor);
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			for (int i = 0; i < 5 ;i++) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(l + ": In async 2 thread for seed: " + l);
			}
			return "NULL String";
			
		}, executor);
		
		
		CompletableFuture<String> lll1 = future2.thenApply(s -> {
			System.out.println(l + ": Completable Future Finished " + " ===> " + s);
			return l + " ===> " + s;
		});
		
		future2.thenApply(s -> {
			System.out.println(l + ": step 1 process."  + s);
			return l + ": step 1 process. " + s;
		})
		.thenApply(s -> {
			System.out.println(l + ": step 2 process." + s);
			return l + ": step 2 process." + s;
		})
		.thenAccept(s -> {
			System.out.println(l + ": step 3 process." + s);
		})
		.thenRun(() -> {
			System.out.println(l + ": step 4, final process.");
		})
		.thenRun(() -> {
			System.out.println(l + ": step 5, finish up.");
		})
		.thenRunAsync(() -> {
			System.out.println(l + ": step 6, finish up in another thread.");
		});
		
		getUsersDetail("8888")
			.thenApply(u -> getCreditRating(u));
		
		getUsersDetail("9999")
			.thenCompose(u -> getCreditRating(u));
		
		
		try {
			int r = weight1().thenCombine(weight2(), (i1, i2) -> {
				return i1 + i2;
			}).get();
			System.out.println("Combine number i1, i2 = " + r);
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
//		try {
//			String freturn = lll1.get();
//			System.out.println(l + ": final result : " + freturn);
//		} catch (InterruptedException | ExecutionException e1) {
//			e1.printStackTrace();
//		}
		
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Process subscribe: " + l);

	}
	
	private CompletableFuture<String> getUsersDetail(String userId) {
		return CompletableFuture.supplyAsync(() -> {
			System.out.println("UserId: " + userId);
			try {
				Thread.sleep(1100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "I am the user with ID: " + userId;
		});	
	}

	private CompletableFuture<String> getCreditRating(String user) {
		return CompletableFuture.supplyAsync(() -> {
			System.out.println("Processing user: " + user);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "bb";
		});
	}
	
	private CompletableFuture<Integer> weight1() {
		return CompletableFuture.supplyAsync(() ->{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 12;
		});
	}
	
	private CompletableFuture<Integer> weight2() {
		return CompletableFuture.supplyAsync(() ->{
			try {
				Thread.sleep(4500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 21;
		});
	}


}

