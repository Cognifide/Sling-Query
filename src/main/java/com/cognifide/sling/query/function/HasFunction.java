package com.cognifide.sling.query.function;

import java.util.Iterator;

import com.cognifide.sling.query.IteratorUtils;
import com.cognifide.sling.query.api.SearchStrategy;
import com.cognifide.sling.query.api.TreeProvider;
import com.cognifide.sling.query.api.function.ElementToElementFunction;
import com.cognifide.sling.query.iterator.EmptyElementFilter;
import com.cognifide.sling.query.iterator.IteratorFactory;
import com.cognifide.sling.query.selector.Option;
import com.cognifide.sling.query.selector.SelectorFunction;

public class HasFunction<T> implements ElementToElementFunction<T> {

	private final FindFunction<T> findFunction;

	private final SelectorFunction<T> selector;

	public HasFunction(String selectorString, SearchStrategy searchStrategy, TreeProvider<T> provider) {
		this.findFunction = new FindFunction<T>(selectorString, searchStrategy, provider);
		this.selector = SelectorFunction.parse(selectorString, searchStrategy, provider);
	}

	@Override
	public T apply(T input) {
		Iterator<Option<T>> iterator = IteratorUtils.singleElementIterator(new Option<T>(input));
		iterator = IteratorFactory.getOptionIterator(findFunction, iterator);
		iterator = selector.apply(iterator);
		if (new EmptyElementFilter<T>(iterator).hasNext()) {
			return input;
		} else {
			return null;
		}
	}
}