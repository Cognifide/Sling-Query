package com.cognifide.sling.query;

import java.util.Iterator;
import java.util.List;

import com.cognifide.sling.query.api.Predicate;
import com.cognifide.sling.query.selector.parser.Attribute;

public interface TreeProvider<T> {
	Iterator<T> listChildren(T parent);

	T getParent(T element);

	String getName(T element);

	Predicate<T> getPredicate(String type, String id, List<Attribute> attributes);

	Iterator<T> query(String selector, T resource);
}