package com.cognifide.sling.query;

import java.util.Iterator;

import org.apache.sling.api.resource.Resource;

import com.cognifide.sling.query.api.Function;
import com.cognifide.sling.query.api.function.IteratorToIteratorFunction;
import com.cognifide.sling.query.api.function.ResourceToIteratorFunction;
import com.cognifide.sling.query.api.function.ResourceToResourceFunction;
import com.cognifide.sling.query.iterator.FunctionIterator;
import com.cognifide.sling.query.iterator.ResourceTransformerIterator;

public final class IteratorFactory {
	private IteratorFactory() {
	}

	public Iterator<Resource> getIterator(Function<?, ?> function, Iterator<Resource> parentIterator) {
		if (function instanceof ResourceToResourceFunction) {
			return new ResourceTransformerIterator((ResourceToResourceFunction) function, parentIterator);
		} else if (function instanceof ResourceToIteratorFunction) {
			return new FunctionIterator((ResourceToIteratorFunction) function, parentIterator);
		} else if (function instanceof IteratorToIteratorFunction) {
			return ((IteratorToIteratorFunction) function).apply(parentIterator);
		} else {
			throw new IllegalArgumentException("Don't know how to handle " + function.toString());
		}
	}
}