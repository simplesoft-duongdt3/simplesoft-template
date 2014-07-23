/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * CollectionUtil.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 22:42:21 23 Jul 2014
 */
public class CollectionUtil {
	public static abstract class ICondition<T> {
		private Operator op = Operator.IS;
		public final ICondition<T> setOperator(Operator op) {
			this.op = op;
			return this;
		}
		
		protected abstract boolean doCondition(T object);
		
		public final boolean isVailCondition(T object){
			boolean re = doCondition(object);
			return (this.op == Operator.NOT? !re :re);
			
		}
	}

	public static abstract class IConditionDoAction<T> {
		protected abstract boolean isVailCondition(T object);

		protected abstract void doAction(T object);
	}
	
	public static enum Operator{
		IS,
		NOT
	}
	
	public static enum OrderBy{
		ASC(1),
		DESC(-1);
		
		private int value;
		private OrderBy(int pValue) {
			this.value = pValue;
		}
		
		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}
	}
	
	public static abstract class IComparator<T> implements Comparator<T> {

		private int way = OrderBy.ASC.value;
		
		public final IComparator<T> setWay(OrderBy pWay) {
			this.way = pWay.getValue();
			return this;
		}
		
		protected abstract int doCompare(T lhs, T rhs);
		
		@Override
		public final int compare(T lhs, T rhs) {
			return way * doCompare(lhs, rhs);
		}
		
		public final int getWay() {
			return way;
		}
	}
	
	public static class MultiComparator<T> implements Comparator<T> {

		List<IComparator<? super T>> coms = new ArrayList<CollectionUtil.IComparator<? super T>>();

		public MultiComparator() {
			
		}
		
		public MultiComparator<T> addComparator(IComparator<? super T> pCom){
			if (pCom != null) {
				this.coms.add(pCom);
			}
			return this;
		}
		
		@Override
		public final int compare(T lhs, T rhs) {
			int result = 0;
			if (this.coms != null) {
				for (IComparator<? super T> com : this.coms) {
					result = com.compare(lhs, rhs);
					if (result != 0) {
						break;
					}
				}
			}
			return result;
		}
	}
	
	public static class MultiCondition<T> extends ICondition<T> {

		List<ICondition<? super T>> cons = new ArrayList<ICondition<? super T>>();

		public MultiCondition<T> addCondition(ICondition<? super T> pCon){
			if (pCon != null) {
				this.cons.add(pCon);
			}
			return this;
		}
		
		@Override
		protected boolean doCondition(T object) {
			boolean result = true;
			if (this.cons != null && !this.cons.isEmpty()) {
				for (ICondition<? super T> com : this.cons) {
					result = com.isVailCondition(object);
					if (!result) {
						break;
					}
				}
			} else {
				result = false;
			}
			return result;
		}
	}
	
	public static <T> List<T> filter(List<T> list, ICondition<? super T> condition) {
		List<T> result = new ArrayList<T>();
	    for (T element: list) {
	    	//nếu thoả thì add vào mảng
			if (condition.isVailCondition(element)) {
				result.add(element);
			}
	    }
	    
	    return result;
	}
	
	public static <T> List<T> filterAndDoAction(List<T> list, IConditionDoAction<? super T> condition) {
		List<T> result = new ArrayList<T>();
	    for (T element: list) {
	        if (condition.isVailCondition(element)) {
	        	condition.doAction(element);
	            result.add(element);
	        }
	    }
	    return result;
	}
	
	public static <T> List<T> filterNot(List<T> list, ICondition<? super T> condition) {
		List<T> result = new ArrayList<T>();
	    for (T element: list) {
	    	//chỉ cần ko thoả thì thêm vào mảng
	        if (!condition.isVailCondition(element)) {
	            result.add(element);
	        }
	    }
	    return result;
	}
	
	public static <T> List<T> filterNotAndDoAction(List<T> list, IConditionDoAction<? super T> condition) {
		List<T> result = new ArrayList<T>();
	    for (T element: list) {
	        if (!condition.isVailCondition(element)) {
	        	condition.doAction(element);
	            result.add(element);
	        }
	    }
	    return result;
	}
	
	public static <T> void filterIn(List<T> list, ICondition<? super T> condition) {
		for (int i = list.size() - 1; i >= 0 ; i--) {
	    	//ko thoả thì bỏ khỏi mảng
	        if (!condition.isVailCondition(list.get(i))) {
	            list.remove(i);
	        }
		}
	}
	
	public static <T> void filterInNot(List<T> list, ICondition<? super T> condition) {
		for (int i = list.size() - 1; i >= 0 ; i--) {
	    	//nếu thoả thì bỏ khỏi mảng
			if (condition.isVailCondition(list.get(i))) {
				list.remove(i);
			}
		}
	}
	
	public static <T> void filterInAndDoAction(List<T> list, IConditionDoAction<? super T> condition) {
		 for (T element: list) {
			if (condition.isVailCondition(element)) {
				condition.doAction(element);
	        }
		}
	}
	
	public static <T> void filterInNotAndDoAction(List<T> list, IConditionDoAction<? super T> condition) {
		 for (T element: list) {
			if (!condition.isVailCondition(element)) {
				condition.doAction(element);
			}
		}
	}
	
	public static <T> T firstElement(List<T> list, ICondition<? super T> condition) {
		T result = null;
	    for (T element: list) {
	        if (condition.isVailCondition(element)) {
	        	result = element;
	        	break;
	        }
	    }
	    return result;
	}
	
	public static <T> T lastElement(List<T> list, ICondition<? super T> condition) {
		T result = null;
		for (int i = list.size() - 1; i >= 0 ; i--) {
			if (condition.isVailCondition(list.get(i))) {
				result = list.get(i);
	        	break;
	        }
		}
	    return result;
	}
	
	public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
		Collections.sort(list, comparator);
	}
	
	public static <T> void reverse(List<T> list) {
		Collections.reverse(list);
	}
	
	public static <T> T max(List<T> list, Comparator<? super T> comparator) {
		return Collections.max(list, comparator);
	}
	
	public static <T> List<T> maxList(List<T> list, final Comparator<? super T> comparator) {
		List<T> result = new ArrayList<T>();
		final T min = max(list, comparator);
		if (min != null) {
			result = filter(list, new ICondition<T>() {
				@Override
				protected boolean doCondition(T object) {
					return comparator.compare(object, min) == 0;
				}
			});
		}
		return result;
	}
	
	public static <T> T min(List<T> list, Comparator<? super T> comparator) {
		return Collections.min(list, comparator);
	}
	
	public static <T> List<T> minList(List<T> list, final Comparator<? super T> comparator) {
		List<T> result = new ArrayList<T>();
		final T min = min(list, comparator);
		if (min != null) {
			result = filter(list, new ICondition<T>() {
				@Override
				protected boolean doCondition(T object) {
					return comparator.compare(object, min) == 0;
				}
			});
		}
		return result;
	}
	
	public static <T> int count(List<T> list, ICondition<? super T> condition) {
		int count = 0;
		for (T element: list) {
	        if (condition.isVailCondition(element)) {
	        	count ++;
	        }
	    }
		return count;
	}
	
	public static <T> int countNot(List<T> list, ICondition<? super T> condition) {
		int size = list.size();
		int count = count(list, condition);
		int countNot = size - count;
		return countNot;
	}
	
	
}
