package com.pnfsoftware.jeb.util.serialization;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class NativeTypeIdProvider extends AbstractTypeIdProvider {
   private static NativeTypeIdProvider instance;

   public static ITypeIdProvider getInstance() {
      if (instance == null) {
         instance = new NativeTypeIdProvider();
      }

      return instance;
   }

   private NativeTypeIdProvider() {
   }

   @Override
   protected void loadTypes(Map var1, Map var2) {
      var1.put(Object.class, -1);
      var1.put(String.class, -2);
      var1.put(BigInteger.class, -3);
      var1.put(BigDecimal.class, -4);
      var1.put(AtomicBoolean.class, -5);
      var1.put(AtomicInteger.class, -6);
      var1.put(AtomicLong.class, -7);
      var1.put(Class.class, -9);
      var1.put(Boolean.class, -10);
      var1.put(Byte.class, -11);
      var1.put(Character.class, -12);
      var1.put(Short.class, -13);
      var1.put(Integer.class, -14);
      var1.put(Long.class, -15);
      var1.put(Float.class, -16);
      var1.put(Double.class, -17);
      var1.put(StringBuilder.class, -18);
      var1.put(boolean[].class, -20);
      var1.put(byte[].class, -21);
      var1.put(char[].class, -22);
      var1.put(short[].class, -23);
      var1.put(int[].class, -24);
      var1.put(long[].class, -25);
      var1.put(float[].class, -26);
      var1.put(double[].class, -27);
      var1.put(ArrayList.class, -30);
      var1.put(HashSet.class, -31);
      var1.put(HashMap.class, -32);
      var1.put(TreeMap.class, -33);
      var1.put(TreeSet.class, -34);
      var1.put(LinkedList.class, -35);
      var1.put(LinkedHashMap.class, -36);
      var1.put(IdentityHashMap.class, -37);
      var1.put(ArrayDeque.class, -38);
      var1.put(LinkedHashSet.class, -39);
      var1.put(ConcurrentSkipListSet.class, -50);
      var1.put(CopyOnWriteArraySet.class, -51);
      var1.put(CopyOnWriteArrayList.class, -52);
      var1.put(ConcurrentHashMap.class, -53);
      var1.put(ConcurrentSkipListMap.class, -54);
      var1.put(ConcurrentLinkedQueue.class, -55);
      var1.put(SoftReference.class, -60);
      var1.put(WeakReference.class, -61);
      var1.put(AbstractList.class, -62);
   }
}
