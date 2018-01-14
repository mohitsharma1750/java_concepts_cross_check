package com.amdocs.mainApp_12;

import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.scene.shape.VLineTo;

/**
 * Creating streams from functions
 * We can have a function that can generate values on demand
 * 
 * The following two static methods of Strema interface generates infinite stream from function
 * 	--> <T> Stream<T> iterate(T seed, UnaryOperator<T> f);
 * 	--> <T> Stream<T> generate(Supplier<T> supplier);
 * 
 * iterate() method creates sequential ordered stream
 * generate() method creates sequential unordered stream
 * 
 * IntStream, LongStream and DoubleStream contains iterate() and generate() methods,
 * that takes parameters specific to their primitive types and generates values.
 * 
 * 	For Example from IntStream interface
 * 			1. IntStream iterate(int seed, IntUnaryOperator f);
 * 			2. IntStream generate(IntSupplier s);
 * 
 * 
 * A seed is a first element of the stream, the second element is generated by applying the function to the first element and so forth..
 * 
 * 
 * 	Skip method to discard some elements from stream!
 * 	the skip(long n), an intermediate operations --> skips the first n elements of the stream
 * 	
 * 
 * Use of Random class with stream API to create infinite stream of data
 * for this we have ints(), longs(), doubles() method in IntStream, LongStream, DoubleStream respectively.
 * 
 * @author amit
 *
 */
public class MainApp_12_7 {

	public static void main(String[] args) {
//		Generating first 10 natual numbers!
		Stream<Long> tenNatualNumbers = Stream.iterate(1L, n -> n + 1).limit(10);
		tenNatualNumbers.forEach(System.out::println);
		
		System.out.println("===========================================");
//		let's filter the values generated by iterate() method
		Predicate<Long> oddPredicate = n -> {
			if(n %2 == 0) {
				return false;
			}
			else {
				return true;
			}
		};
		
		Stream.iterate(2L, n -> n + 1)
			.filter(oddPredicate)
			.limit(10)
			.filter(oddPredicate)
			.forEach(System.out::println);
		
		System.out.println("================================");
//		The following code skips first 100 odd numbers
		Stream.iterate(1L, n -> n + 1)
			.filter(oddPredicate)
			.skip(100)
			.limit(5)
			.forEach(System.out::println);
		
		System.out.println("=====================================");
//		The following code uses generate method to generate infinite sequential unordered stream
		Stream.generate(UUID::randomUUID)
			.limit(10)
			.forEach(System.out::println);

		System.out.println("=========================================");
//		Generate a stream of elements based on previous one, you need to use the supplier to store the last generated element
		Stream.generate(MainApp_12_7::next)
			.limit(10)
			.forEach(System.out::println);
	
		
		System.out.println("==========================================");
//		use of Random class to generate infinite stream of data
		new Random().ints()
			.limit(10)
			.forEach(System.out::println);
		
		System.out.println("==========================================");
//		We can use nextInt() method of Random class as a supplier in generate method to achieve the same result
		Stream.generate(new Random()::nextInt)
			.limit(10)
			.forEach(System.out::println);
		
		System.out.println("========================================");
//		to work only with primitive types, use the generate() method of the primitive type's stream interface
		IntStream.generate(new Random()::nextInt)
			.limit(5)
			.forEach(System.out::println);
		
		System.out.println("=========================================");
//		to generate an infinite stream of a repeating value, use lambda expression with returning same constant every time
		IntStream.generate(() -> 0)
			.limit(5)
			.forEach((value) -> {System.out.println(value);});
		
		
	}
	
	static int i=0;
	public static int next() {
		i++;
		return i;
	}
}